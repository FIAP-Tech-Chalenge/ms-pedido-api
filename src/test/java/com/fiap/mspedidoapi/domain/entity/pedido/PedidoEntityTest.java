package com.fiap.mspedidoapi.domain.entity.pedido;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoEntityTest {
    private PedidoEntity pedidoEntity;
    private UUID clienteUuid;

    @BeforeEach
    public void setUp() {
        clienteUuid = UUID.randomUUID();
        pedidoEntity = new PedidoEntity(clienteUuid);
    }

    @Test
    public void testConstructorWithClienteUuid() {
        assertNotNull(pedidoEntity);
        assertEquals(clienteUuid, pedidoEntity.getClienteUuid());
        assertNotNull(pedidoEntity.getProdutos());
        assertTrue(pedidoEntity.getProdutos().isEmpty());
    }

    @Test
    public void testConstructorWithAllFields() {
        UUID pedidoId = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();
        StatusPedido statusPedido = StatusPedido.RECEBIDO;
        StatusPagamento statusPagamento = StatusPagamento.AGUARDANDO_PAGAMENTO;
        Float total = 100.0f;

        PedidoEntity pedidoEntityFull = new PedidoEntity(pedidoId, clienteId, statusPedido, statusPagamento, total);

        assertNotNull(pedidoEntityFull);
        assertEquals(pedidoId, pedidoEntityFull.getPedidoId());
        assertEquals(clienteId, pedidoEntityFull.getClienteUuid());
        assertEquals(statusPedido, pedidoEntityFull.getStatusPedido());
        assertEquals(statusPagamento, pedidoEntityFull.getStatusPagamento());
        assertEquals(total, pedidoEntityFull.getTotal());
    }

    @Test
    public void testSettersAndGetters() {
        UUID pedidoId = UUID.randomUUID();
        pedidoEntity.setPedidoId(pedidoId);
        assertEquals(pedidoId, pedidoEntity.getPedidoId());

        String uuid = UUID.randomUUID().toString();
        pedidoEntity.setUuid(uuid);
        assertEquals(uuid, pedidoEntity.getUuid());

        int numeroPedido = 12345;
        pedidoEntity.setNumeroPedido(numeroPedido);
        assertEquals(numeroPedido, pedidoEntity.getNumeroPedido());

        StatusPedido statusPedido = StatusPedido.EM_PREPARACAO;
        pedidoEntity.setStatusPedido(statusPedido);
        assertEquals(statusPedido, pedidoEntity.getStatusPedido());

        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        pedidoEntity.setStatusPagamento(statusPagamento);
        assertEquals(statusPagamento, pedidoEntity.getStatusPagamento());

        Float total = 200.0f;
        pedidoEntity.setTotal(total);
        assertEquals(total, pedidoEntity.getTotal());
    }

    @Test
    public void testAddProdutoAndValorTotalDoPedido() {
        ProdutoEntity produto1 = new ProdutoEntity(UUID.randomUUID(), "Produto 1", 2, CategoriaEnum.LANCHE);
        produto1.setValor(10.0f);
        ProdutoEntity produto2 = new ProdutoEntity(UUID.randomUUID(), "Produto 2", 1, CategoriaEnum.BEBIDA);
        produto2.setValor(15.0f);

        pedidoEntity.addProduto(produto1);
        pedidoEntity.addProduto(produto2);

        assertEquals(2, pedidoEntity.getProdutos().size());
        assertEquals(produto1, pedidoEntity.getProdutos().get(0));
        assertEquals(produto2, pedidoEntity.getProdutos().get(1));
        assertEquals(35.0f, pedidoEntity.valorTotalDoPedido());
    }

    @Test
    public void testNullAndInvalidValues() {
        pedidoEntity.setPedidoId(null);
        assertNull(pedidoEntity.getPedidoId());

        pedidoEntity.setUuid(null);
        assertNull(pedidoEntity.getUuid());

        pedidoEntity.setNumeroPedido(-1);
        assertEquals(-1, pedidoEntity.getNumeroPedido());

        pedidoEntity.setStatusPedido(null);
        assertNull(pedidoEntity.getStatusPedido());

        pedidoEntity.setStatusPagamento(null);
        assertNull(pedidoEntity.getStatusPagamento());

        pedidoEntity.setTotal(-100.0f);
        assertEquals(-100.0f, pedidoEntity.getTotal());
    }
}
