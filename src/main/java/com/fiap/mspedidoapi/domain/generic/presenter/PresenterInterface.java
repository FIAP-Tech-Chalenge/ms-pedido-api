package com.fiap.mspedidoapi.domain.generic.presenter;

import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;

import java.util.Map;

public interface PresenterInterface {
    Map<String, Object> toArray();

    OutputInterface getOutput();
}
