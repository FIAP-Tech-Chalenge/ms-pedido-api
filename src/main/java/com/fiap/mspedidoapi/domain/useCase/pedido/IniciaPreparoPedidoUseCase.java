package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class IniciaPreparoPedidoUseCase {
    
    private final BuscaListaPedidoInterface buscaListaPedidoInterface;
    private OutputInterface outputInterface;

    public void execute(UUID pedidoUUID, Integer tempoDePreparoEmMinutos) {
        try {
            Pedido pedidoEncontrado = buscaListaPedidoInterface.encontraPedidoPorUuid(pedidoUUID);
        
            if(pedidoEncontrado.getStatusPedido() != StatusPedido.RECEBIDO) {
                this.outputInterface = new PedidoProntoOutput(
                        null,
                        new OutputStatus(404, "Not found", "Pedido precisa estar com status RECEBIDO para dar inicio ao preparo")
                );
                return;
            }

            Entrega entrega = buscaListaPedidoInterface.movePedidoParaEmPreparacao(pedidoUUID, tempoDePreparoEmMinutos);
            this.outputInterface = new PedidoProntoOutput(
                entrega,
                new OutputStatus(200, "OK", "Pedido atualizado.")
            );

        }catch (PedidoNaoEncontradoException e) {
            this.outputInterface = new PedidoProntoOutput(
                    null,
                    new OutputStatus(404, "Not found", "Pedido não encontrado")
            );
        } catch (Exception e) {
            outputInterface = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
    
}
