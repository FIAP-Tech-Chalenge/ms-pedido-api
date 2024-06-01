package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.list;

import com.fiap.mspedidoapi.application.response.GenericResponse;
import com.fiap.mspedidoapi.application.response.PresenterResponse;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.mspedidoapi.domain.presenters.pedido.GetPedidosPresenter;
import com.fiap.mspedidoapi.domain.useCase.pedido.BuscaListaPedidosUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.BuscarListaPedidoRepository;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("cozinha/pedido")
public class ListPedidosController {
    private final PedidosMongoRepository pedidosMongoRepository;

    @GetMapping("/lista-pedido/")
    @Operation(tags = {"cozinha"})
    public ResponseEntity<Object> getAllListaPedidos() {
        //injetar repository do que consulta o MONGODB
        BuscaListaPedidosUseCase useCase = new BuscaListaPedidosUseCase(new BuscarListaPedidoRepository(pedidosMongoRepository));
        useCase.execute();
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetPedidosPresenter presenter = new GetPedidosPresenter((BuscaTodosPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


