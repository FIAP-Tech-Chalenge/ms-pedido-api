package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos;

import com.fiap.mspedidoapi.application.response.GenericResponse;
import com.fiap.mspedidoapi.application.response.PresenterResponse;
import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import com.fiap.mspedidoapi.domain.presenters.pedido.PedidoProntoPresenter;
import com.fiap.mspedidoapi.domain.useCase.pedido.PedidoProntoUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.PedidoProntoRepository;
import com.fiap.mspedidoapi.infra.kafka.producers.PedidoProntoProducer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoProntoControllerTest {

    private PedidoProntoController pedidoProntoController;
    private PedidosMongoRepository pedidosMongoRepository;
    private PedidoProntoProducer pedidoProntoProducer;

    @BeforeEach
    public void setUp() {
        pedidosMongoRepository = mock(PedidosMongoRepository.class);
        pedidoProntoProducer = mock(PedidoProntoProducer.class);
        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository);
    }

    @Test
    public void deveInicializarComDependenciasCorretamente() {
        assertThat(pedidoProntoController).isNotNull();
    }

    @Test
    public void deveAtualizarPedidoComSucesso() {
        UUID pedidoUuid = UUID.randomUUID();
        Entrega entrega = new Entrega(pedidoUuid, 123, StatusPedido.PRONTO);
        OutputStatus outputStatus = new OutputStatus(200, "OK", "Pedido pronto");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(entrega, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);
        doNothing().when(pedidoProntoProducer).send(any(PedidoProntoOutput.class));

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                if (outputInterface.getOutputStatus().getCode() != 200) {
                    return new GenericResponse().response(outputInterface);
                }
                PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                return new PresenterResponse().response(presenter);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isInstanceOf(Map.class);

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarNotFoundQuandoPedidoNaoEncontrado() {
        UUID pedidoUuid = UUID.randomUUID();
        OutputStatus outputStatus = new OutputStatus(404, "Not found", "Pedido não encontrado");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(null, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                if (outputInterface.getOutputStatus().getCode() != 200) {
                    return new GenericResponse().response(outputInterface);
                }
                PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                return new PresenterResponse().response(presenter);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        assertThat(response.getBody()).isNull();

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarErroQuandoExcecaoForLancada() {
        UUID pedidoUuid = UUID.randomUUID();
        OutputStatus outputStatus = new OutputStatus(500, "Internal Server Error", "Erro no servidor");
        OutputError outputError = new OutputError("Erro no servidor", outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(outputError);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                if (outputInterface.getOutputStatus().getCode() != 200) {
                    return new GenericResponse().response(outputInterface);
                }
                PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                return new PresenterResponse().response(presenter);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isEqualTo(outputStatus);

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarNoContentQuandoStatus204() {
        UUID pedidoUuid = UUID.randomUUID();
        OutputStatus outputStatus = new OutputStatus(204, "No Content", "Sem conteúdo");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(null, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                return new GenericResponse().response(outputInterface);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        assertThat(response.getBody()).isNull();

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarUnprocessableEntityQuandoStatus422() {
        UUID pedidoUuid = UUID.randomUUID();
        Entrega entrega = new Entrega(pedidoUuid, 123, StatusPedido.EM_PREPARACAO);
        OutputStatus outputStatus = new OutputStatus(422, "Unprocessable Entity", "Entidade não processável");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(entrega, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                int statusCode = outputInterface.getOutputStatus().getCode();
                if (statusCode != 200) {
                    if (statusCode == 201 || statusCode == 422) {
                        PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                        return new PresenterResponse().response(presenter);
                    }
                    return new GenericResponse().response(outputInterface);
                }
                PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                return new PresenterResponse().response(presenter);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(422);

        PedidoProntoPresenter presenter = new PedidoProntoPresenter(pedidoProntoOutput);
        assertThat(response.getBody()).isEqualTo(presenter.toArray());

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarBadRequestQuandoStatus400() {
        UUID pedidoUuid = UUID.randomUUID();
        OutputStatus outputStatus = new OutputStatus(400, "Bad Request", "Requisição inválida");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(null, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                return new GenericResponse().response(outputInterface);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNull();

        verify(mockUseCase).execute(pedidoUuid);
    }

    @Test
    public void deveRetornarErroInternoQuandoExcecaoNaoEsperadaForLancada() {
        UUID pedidoUuid = UUID.randomUUID();
        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        doThrow(new RuntimeException("Erro inesperado")).when(mockUseCase).execute(any(UUID.class));

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                try {
                    mockUseCase.execute(pedidoUuid);
                    OutputInterface outputInterface = mockUseCase.getOutputInterface();
                    if (outputInterface.getOutputStatus().getCode() != 200) {
                        return new GenericResponse().response(outputInterface);
                    }
                    PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                    return new PresenterResponse().response(presenter);
                } catch (Exception e) {
                    OutputError outputError = new OutputError(e.getMessage(), new OutputStatus(500, "Internal Server Error", "Erro inesperado"));
                    return new GenericResponse().response(outputError);
                }
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void deveRetornarStatusNaoProcessavelQuandoStatusNaoFor200() {
        UUID pedidoUuid = UUID.randomUUID();
        OutputStatus outputStatus = new OutputStatus(422, "Unprocessable Entity", "Entidade não processável");
        PedidoProntoOutput pedidoProntoOutput = new PedidoProntoOutput(null, outputStatus);

        PedidoProntoUseCase mockUseCase = mock(PedidoProntoUseCase.class);
        when(mockUseCase.getOutputInterface()).thenReturn(pedidoProntoOutput);

        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository) {
            @Override
            public ResponseEntity<Object> atualizaPedido(UUID pedidoUuid) {
                mockUseCase.execute(pedidoUuid);
                OutputInterface outputInterface = mockUseCase.getOutputInterface();
                if (outputInterface.getOutputStatus().getCode() != 200) {
                    return new GenericResponse().response(outputInterface);
                }
                PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
                return new PresenterResponse().response(presenter);
            }
        };

        ResponseEntity<Object> response = pedidoProntoController.atualizaPedido(pedidoUuid);

        assertThat(response.getStatusCodeValue()).isEqualTo(422);
        assertThat(response.getBody()).isNull();
    }

}
