package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.Pedido;
import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class BuscaTodosPedidoOutput implements OutputInterface {
    private List<Pedido> listPedidos;
    private OutputStatus outputStatus;

    public BuscaTodosPedidoOutput(List<Pedido> listPedidos, OutputStatus outputStatus) {
        this.listPedidos = listPedidos;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.listPedidos;
    }
}
