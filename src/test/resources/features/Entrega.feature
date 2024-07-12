Feature: Entrega entity

  Scenario: Inicializar Entrega com valores corretos
    Given que uma Entrega é inicializada
    Then os atributos da Entrega devem ser corretamente definidos

  Scenario: Atualizar uuidPedido da Entrega
    Given que uma Entrega é inicializada
    When o uuidPedido é atualizado
    Then o novo uuidPedido deve ser corretamente definido

  Scenario: Atualizar numeroPedido da Entrega
    Given que uma Entrega é inicializada
    When o numeroPedido é atualizado
    Then o novo numeroPedido deve ser corretamente definido

  Scenario: Atualizar statusPedido da Entrega
    Given que uma Entrega é inicializada
    When o statusPedido é atualizado
    Then o novo statusPedido deve ser corretamente definido

  Scenario: Comparar Entrega com objetos diferentes
    Given que uma Entrega é inicializada
    Then deve retornar false para equals com objeto diferente

  Scenario: Comparar Entrega com o mesmo objeto
    Given que uma Entrega é inicializada
    Then deve retornar true para equals com o mesmo objeto

  Scenario: Comparar Entrega com objeto de mesmo conteúdo
    Given que uma Entrega é inicializada
    Then deve retornar true para equals com mesmo conteúdo

  Scenario: Comparar Entrega com objeto de conteúdo diferente
    Given que uma Entrega é inicializada
    Then deve retornar false para equals com conteúdo diferente

  Scenario: Comparar hashCode de objetos iguais
    Given que uma Entrega é inicializada
    Then hashCode deve ser igual para objetos iguais

  Scenario: Comparar hashCode de objetos diferentes
    Given que uma Entrega é inicializada
    Then hashCode deve ser diferente para objetos diferentes

  Scenario: Verificar toString da Entrega
    Given que uma Entrega é inicializada
    Then toString deve retornar a string correta
