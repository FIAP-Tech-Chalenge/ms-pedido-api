package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.list;

import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.mspedidoapi.domain.useCase.pedido.BuscaListaPedidosUseCase;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.BuscarListaPedidoRepository;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ListPedidosControllerTest {

    @Mock
    private PedidosMongoRepository pedidosMongoRepository;

    @InjectMocks
    private ListPedidosController listPedidosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllListaPedidosSuccess() {
        BuscarListaPedidoRepository buscarListaPedidoRepository = mock(BuscarListaPedidoRepository.class);
        BuscaListaPedidosUseCase useCase = mock(BuscaListaPedidosUseCase.class);

        when(useCase.getBuscaProdutoOutput()).thenReturn(new BuscaTodosPedidoOutput());

        ResponseEntity<Object> responseEntity = listPedidosController.getAllListaPedidos();

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(pedidosMongoRepository, times(1)).findAll();
    }

    @Test
    void testGetAllListaPedidosError() {
        doThrow(new RuntimeException("Erro no servidor")).when(pedidosMongoRepository).findAll();
        ResponseEntity<Object> responseEntity = listPedidosController.getAllListaPedidos();
        assertEquals(500, responseEntity.getStatusCodeValue());
    }
}
