Feature: Buscar Lista de Pedidos

  Scenario: Deve retornar lista de pedidos com sucesso
    Given que existem pedidos no repositório
    When a lista de pedidos é buscada
    Then deve retornar uma lista de pedidos com sucesso

  Scenario: Deve retornar lista vazia quando não há pedidos
    Given que não existem pedidos no repositório
    When a lista de pedidos é buscada
    Then deve retornar uma lista vazia
