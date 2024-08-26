package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class BuscaListaPedidosUseCaseTest {

    private BuscaListaPedidosUseCase useCase;
    private BuscaListaPedidoInterface buscaListaPedidoInterface;

    @BeforeEach
    public void setUp() {
        buscaListaPedidoInterface = Mockito.mock(BuscaListaPedidoInterface.class);
        useCase = new BuscaListaPedidosUseCase(buscaListaPedidoInterface);
    }

    @Test
    public void deveExecutarBuscaListaPedidosComSucesso() {
        PedidoEntity pedido1 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null,20, 100.0f);
        PedidoEntity pedido2 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null,20, 200.0f);
        List<PedidoEntity> pedidos = List.of(pedido1, pedido2);

        when(buscaListaPedidoInterface.findListaPedidos()).thenReturn(pedidos);

        useCase.execute();

        OutputInterface output = useCase.getBuscaProdutoOutput();
        assertThat(output).isInstanceOf(BuscaTodosPedidoOutput.class);
        BuscaTodosPedidoOutput buscaTodosPedidoOutput = (BuscaTodosPedidoOutput) output;

        assertThat(buscaTodosPedidoOutput.getListPedidos()).isEqualTo(pedidos);
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getCode()).isEqualTo(200);
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getCodeName()).isEqualTo("OK");
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getMessage()).isEqualTo("Lista de pedidos");
    }

    @Test
    public void deveRetornarErroQuandoFalhaAoBuscarListaPedidos() {
        String errorMessage = "Erro ao buscar lista de pedidos";
        doThrow(new RuntimeException(errorMessage)).when(buscaListaPedidoInterface).findListaPedidos();

        useCase.execute();

        OutputInterface output = useCase.getBuscaProdutoOutput();
        assertThat(output).isInstanceOf(OutputError.class);
        OutputError outputError = (OutputError) output;

        assertThat(outputError.getMensagem()).isEqualTo(errorMessage);
        assertThat(outputError.getOutputStatus().getCode()).isEqualTo(500);
        assertThat(outputError.getOutputStatus().getCodeName()).isEqualTo("Internal Server Error");
        assertThat(outputError.getOutputStatus().getMessage()).isEqualTo("Erro no servidor");
    }

    @Test
    public void deveRetornarListaVaziaQuandoNaoHaPedidos() {
        List<PedidoEntity> pedidos = List.of();

        when(buscaListaPedidoInterface.findListaPedidos()).thenReturn(pedidos);

        useCase.execute();

        OutputInterface output = useCase.getBuscaProdutoOutput();
        assertThat(output).isInstanceOf(BuscaTodosPedidoOutput.class);
        BuscaTodosPedidoOutput buscaTodosPedidoOutput = (BuscaTodosPedidoOutput) output;

        assertThat(buscaTodosPedidoOutput.getListPedidos()).isEmpty();
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getCode()).isEqualTo(200);
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getCodeName()).isEqualTo("OK");
        assertThat(buscaTodosPedidoOutput.getOutputStatus().getMessage()).isEqualTo("Lista de pedidos");
    }

    @Test
    public void deveRetornarErroQuandoListaDePedidosEhNula() {
        when(buscaListaPedidoInterface.findListaPedidos()).thenReturn(null);

        useCase.execute();

        OutputInterface output = useCase.getBuscaProdutoOutput();
        assertThat(output).isInstanceOf(OutputError.class);
        OutputError outputError = (OutputError) output;

        assertThat(outputError.getMensagem()).isEqualTo("Lista de pedidos é nula");
        assertThat(outputError.getOutputStatus().getCode()).isEqualTo(500);
        assertThat(outputError.getOutputStatus().getCodeName()).isEqualTo("Internal Server Error");
        assertThat(outputError.getOutputStatus().getMessage()).isEqualTo("Erro no servidor");
    }
}
