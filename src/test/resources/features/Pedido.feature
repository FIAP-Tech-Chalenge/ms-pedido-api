Feature: Pedido
  Scenario: Inicializar Pedido Corretamente
    Given que um pedido é inicializado
    Then os atributos do pedido devem ser corretamente definidos

  Scenario: Atualizar Atributos do Pedido
    Given que um pedido é inicializado
    When os atributos do pedido são atualizados
    Then os novos atributos do pedido devem ser corretamente definidos
