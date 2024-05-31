package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.Pedido;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
@Getter
public class BuscaListaPedidosUseCase {

    private final BuscaListaPedidoInterface buscaListaPedidoInterface;
    private OutputInterface buscaProdutoOutput;

    public void execute() {
        try {
            List<Pedido> listPedidos = buscaListaPedidoInterface.findListaPedidos();

            buscaProdutoOutput = new BuscaTodosPedidoOutput(
                    listPedidos,
                    new OutputStatus(200, "OK", "Lista de pedidos")
            );

        } catch (Exception e) {
            buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }

}
