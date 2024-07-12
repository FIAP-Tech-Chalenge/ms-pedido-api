Feature: Pedido Consumer
  Scenario: Consumir mensagem de pedido e salvar no banco de dados
    Given que uma mensagem de pedido está disponível no tópico "topic"
    When o consumidor processa a mensagem
    Then o pedido deve ser salvo no banco de dados
