package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class PedidoEmPreparacaoOutput implements OutputInterface {
    private PedidoEntity pedido;
    private OutputStatus outputStatus;

    public PedidoEmPreparacaoOutput(PedidoEntity pedido, OutputStatus outputStatus) {
        this.pedido = pedido;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.pedido;
    }

    @Override
    public OutputStatus getOutputStatus() {
        return this.outputStatus;
    }
    
}
