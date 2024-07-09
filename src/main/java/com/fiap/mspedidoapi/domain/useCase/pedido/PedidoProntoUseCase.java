package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.gateway.pedido.PedidoProntoInterface;
import com.fiap.mspedidoapi.domain.gateway.producers.PedidoProntoProducerInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PedidoProntoUseCase {
    private final PedidoProntoInterface entregaInterface;
    private final PedidoProntoProducerInterface pedidoProntoProducerInterface;
    private OutputInterface outputInterface;

    public PedidoProntoUseCase(PedidoProntoInterface entregaInterface, PedidoProntoProducerInterface pedidoProntoProducerInterface) {
        this.entregaInterface = entregaInterface;
        this.pedidoProntoProducerInterface = pedidoProntoProducerInterface;
    }

    public void execute(UUID uuidPedido) {
        try {
            Entrega entrega = entregaInterface.atualizaStatusPedido(uuidPedido);
            if (entrega == null) {
                this.outputInterface = new PedidoProntoOutput(
                        null,
                        new OutputStatus(404, "Not found", "Pedido não encontrado")
                );
                return;
            }

            if (entrega.getStatusPedido() == StatusPedido.EM_PREPARACAO) {
                this.outputInterface = new PedidoProntoOutput(
                        entrega,
                        new OutputStatus(422, "Unprocessable Entity", "Entidade não processável")
                );
            } else {
                this.outputInterface = new PedidoProntoOutput(
                        entrega,
                        new OutputStatus(200, "OK", "Pedido pronto")
                );
            }
        } catch (Exception e) {
            outputInterface = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        } finally {
            if (this.pedidoProntoProducerInterface != null
                    && this.outputInterface instanceof PedidoProntoOutput &&
                    this.outputInterface.getOutputStatus().getCode() == 200
            ) {
                this.pedidoProntoProducerInterface.send((PedidoProntoOutput) outputInterface);
            }
        }
    }
}
