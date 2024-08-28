package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.mspedidoapi.domain.gateway.pedido.PreparaPedidoInterface;
import com.fiap.mspedidoapi.domain.gateway.producers.PreparaPedidoProducerInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoEmPreparacaoOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class IniciaPreparoPedidoUseCase {
    
    private final PreparaPedidoInterface preparaPedidoInterface;
    private final PreparaPedidoProducerInterface preparaPedidoProducerInterface;
    private OutputInterface outputInterface;

    public void execute(UUID pedidoUUID, Integer tempoDePreparoEmMinutos) {
        try {

            if(tempoDePreparoEmMinutos <= 0){
                this.outputInterface = new OutputError(
                    "Error", 
                    new OutputStatus(404, "Falha", "Tempo de preparo precisa ser positivo")
                );
                return;
            }

            PedidoEntity pedidoEncontrado = preparaPedidoInterface.encontraPedidoPorUuid(pedidoUUID);

            if(pedidoEncontrado.getStatusPedido() != StatusPedido.RECEBIDO) {
                this.outputInterface = new OutputError(
                    "Error", 
                    new OutputStatus(404, "Status invalido", "Pedido precisa estar com status RECEBIDO para dar inicio ao preparo")
                );
                return;
            }

            PedidoEntity pedidoEmPreparacao = preparaPedidoInterface.movePedidoParaEmPreparacao(pedidoUUID, tempoDePreparoEmMinutos);
            this.outputInterface = new PedidoEmPreparacaoOutput(
                pedidoEmPreparacao,
                new OutputStatus(200, "OK", "Pedido atualizado.")
            );

        }catch (PedidoNaoEncontradoException e) {
            System.out.println("Pedido nao enconstrado");
            this.outputInterface = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not found", "Pedido não encontrado")
            );
        } catch (Exception e) {
            outputInterface = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        } finally {
            if (this.preparaPedidoProducerInterface != null
                    && this.outputInterface instanceof PedidoEmPreparacaoOutput &&
                    this.outputInterface.getOutputStatus().getCode() == 200
            ) {
                this.preparaPedidoProducerInterface.send((PedidoEmPreparacaoOutput) outputInterface);
            }
        }
    }
    
}
