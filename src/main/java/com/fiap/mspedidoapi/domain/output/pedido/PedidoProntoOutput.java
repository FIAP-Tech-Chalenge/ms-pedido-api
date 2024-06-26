package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
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
public class PedidoProntoOutput implements OutputInterface {
    private Entrega entrega;
    private OutputStatus outputStatus;

    public PedidoProntoOutput(Entrega entrega, OutputStatus outputStatus) {
        this.entrega = entrega;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.entrega;
    }
}
