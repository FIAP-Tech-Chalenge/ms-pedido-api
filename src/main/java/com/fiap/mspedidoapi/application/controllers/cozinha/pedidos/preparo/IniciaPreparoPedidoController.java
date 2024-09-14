package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo;

import com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo.requests.StoreIniciaPreparoProdutoRequest;
import com.fiap.mspedidoapi.application.response.GenericResponse;
import com.fiap.mspedidoapi.application.response.PresenterResponse;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoEmPreparacaoOutput;
import com.fiap.mspedidoapi.domain.presenters.pedido.PedidoEmPreparoPresenter;
import com.fiap.mspedidoapi.domain.useCase.pedido.IniciaPreparoPedidoUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.PreparaPedidoRepository;
import com.fiap.mspedidoapi.infra.kafka.producers.PreparaPedidoProducer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cozinha/pedido")
public class IniciaPreparoPedidoController {
    private final PedidosMongoRepository pedidosMongoRepository;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String servers;

    @PostMapping("/inicia-preparo")
    @Operation(summary = "Iniciar preparo de pedido", tags = {"cozinha"})
    @Transactional
    public ResponseEntity<Object> iniciaPreparoPedido(@RequestBody StoreIniciaPreparoProdutoRequest iniciaPreparoProdutoRequest) {
        IniciaPreparoPedidoUseCase useCase = new IniciaPreparoPedidoUseCase(
            new PreparaPedidoRepository(pedidosMongoRepository),
            new PreparaPedidoProducer(servers));
        useCase.execute(iniciaPreparoProdutoRequest.uuid(), iniciaPreparoProdutoRequest.tempoDePreparo());
        OutputInterface outputInterface = useCase.getOutputInterface();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        PedidoEmPreparoPresenter presenter = new PedidoEmPreparoPresenter((PedidoEmPreparacaoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
