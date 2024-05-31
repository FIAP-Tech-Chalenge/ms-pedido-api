package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.produto.Produto;
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
public class BuscaProdutoOutput implements OutputInterface {

    private Produto produto;
    private OutputStatus outputStatus;

    public BuscaProdutoOutput(Produto produto, OutputStatus outputStatus) {
        this.produto = produto;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.produto;
    }
}