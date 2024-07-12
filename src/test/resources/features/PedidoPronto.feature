Feature: Pedido Pronto
  Scenario: Atualizar pedido para pronto
    Given que um pedido existe com UUID "123e4567-e89b-12d3-a456-426614174000"
    When eu atualizo o pedido para pronto
    Then o status do pedido deve ser atualizado para "PRONTO"