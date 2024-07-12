Feature: Pedido Pronto Repository

  Scenario: Deve retornar entrega quando pedido já está pronto
    Given que o pedido com uuid "123e4567-e89b-12d3-a456-426614174000" está com status "PRONTO"
    When o status do pedido é atualizado para pronto
    Then deve retornar a entrega com sucesso

  Scenario: Deve atualizar status para pronto quando não está pronto
    Given que o pedido com uuid "123e4567-e89b-12d3-a456-426614174001" está com status "RECEBIDO"
    When o status do pedido é atualizado para pronto
    Then deve atualizar o status do pedido para "PRONTO"

  Scenario: Deve retornar null quando pedido não encontrado
    Given que o pedido com uuid "123e4567-e89b-12d3-a456-426614174002" não existe
    When o status do pedido é atualizado para pronto
    Then deve retornar null
