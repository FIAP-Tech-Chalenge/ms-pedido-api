Feature: Cliente entity

  Scenario: Inicializar Cliente sem argumentos
    Given que um Cliente é inicializado sem argumentos
    When os valores são definidos
    Then os atributos do Cliente devem ser corretamente definidos

  Scenario: Inicializar Cliente com argumentos
    Given que um Cliente é inicializado com argumentos
    Then os atributos do Cliente devem ser corretamente definidos

  Scenario: Atualizar valores do Cliente
    Given que um Cliente é inicializado com argumentos
    When os valores são atualizados
    Then os novos atributos do Cliente devem ser corretamente definidos

  Scenario: Permitir valores nulos no Cliente
    Given que um Cliente é inicializado sem argumentos
    When os valores são definidos como nulos
    Then os atributos do Cliente devem ser nulos

  Scenario: Verificar toString do Cliente
    Given que um Cliente é inicializado com argumentos
    Then toString deve retornar a string correta
