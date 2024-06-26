package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos;

import com.fiap.mspedidoapi.application.response.GenericResponse;
import com.fiap.mspedidoapi.application.response.PresenterResponse;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import com.fiap.mspedidoapi.domain.presenters.pedido.PedidoProntoPresenter;
import com.fiap.mspedidoapi.domain.useCase.pedido.PedidoProntoUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.PedidoProntoRepository;
import com.fiap.mspedidoapi.infra.kafka.producers.PedidoProntoProducer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("cozinha/entrega")
public class PedidoPontoController {
    private final PedidosMongoRepository pedidosMongoRepository;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String servers;

    @PostMapping("/{pedidoUuid}")
    @Operation(tags = {"cozinha"})
    public ResponseEntity<Object> atualizaPedido(@PathVariable UUID pedidoUuid) {
        PedidoProntoUseCase useCase = new PedidoProntoUseCase(
                new PedidoProntoRepository(pedidosMongoRepository),
                new PedidoProntoProducer(servers));
        useCase.execute(pedidoUuid);
        OutputInterface outputInterface = useCase.getOutputInterface();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
