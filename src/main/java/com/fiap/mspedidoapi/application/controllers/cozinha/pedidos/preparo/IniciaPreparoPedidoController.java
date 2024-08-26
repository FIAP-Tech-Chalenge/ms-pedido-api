package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo;

import com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo.requests.StoreIniciaPreparoProdutoRequest;
import com.fiap.mspedidoapi.application.response.GenericResponse;
import com.fiap.mspedidoapi.application.response.PresenterResponse;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import com.fiap.mspedidoapi.domain.presenters.pedido.PedidoProntoPresenter;
import com.fiap.mspedidoapi.domain.useCase.pedido.IniciaPreparoPedidoUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.BuscarListaPedidoRepository;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cozinha/pedido")
public class IniciaPreparoPedidoController {
    private final PedidosMongoRepository pedidosMongoRepository;

    @PostMapping("/inicia-preparo")
    @Operation(tags = {"cozinha"})
    public ResponseEntity<Object> iniciaPreparoPedido(@RequestBody StoreIniciaPreparoProdutoRequest iniciaPreparoProdutoRequest) {
        IniciaPreparoPedidoUseCase useCase = new IniciaPreparoPedidoUseCase(new BuscarListaPedidoRepository(pedidosMongoRepository));
        useCase.execute(iniciaPreparoProdutoRequest.uuid(), iniciaPreparoProdutoRequest.tempoDePreparo());
        OutputInterface outputInterface = useCase.getOutputInterface();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        PedidoProntoPresenter presenter = new PedidoProntoPresenter((PedidoProntoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
