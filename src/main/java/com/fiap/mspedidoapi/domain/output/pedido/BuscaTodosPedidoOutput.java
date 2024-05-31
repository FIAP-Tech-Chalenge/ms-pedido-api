package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
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
    private List<PedidoEntity> listPedidos;
    private OutputStatus outputStatus;

    public BuscaTodosPedidoOutput(List<PedidoEntity> listPedidos, OutputStatus outputStatus) {
        this.listPedidos = listPedidos;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.listPedidos;
    }
}
