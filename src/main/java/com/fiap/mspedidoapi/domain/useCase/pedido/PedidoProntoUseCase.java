package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.gateway.pedido.PedidoProntoInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PedidoProntoUseCase {
    private final PedidoProntoInterface entregaInterface;
    private OutputInterface outputInterface;

    public PedidoProntoUseCase(PedidoProntoInterface entregaInterface) {
        this.entregaInterface = entregaInterface;
    }

    public void execute(UUID uuidPedido) {
        try {
            Entrega entrega = entregaInterface.atualizaStatusPedido(uuidPedido);
            outputInterface = new PedidoProntoOutput(
                    entrega,
                    new OutputStatus(200, "OK", "Pedido pronto")
            );
        } catch (Exception e) {
            outputInterface = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}
