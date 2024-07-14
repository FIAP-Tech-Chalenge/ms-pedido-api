

## Cobertura de Testes
Para garantir a qualidade do código, foi utilizado o [Jacoco](https://www.eclemma.org/jacoco/) para cobertura de testes, atingindo um percentual total de 85%.

### Rodando os Testes Unitários
Na raiz do projeto, execute o seguinte comando para rodar todos os testes unitários:

```sh
mvn test
```

O relatório de cobertura pode ser encontrado dentro da pasta `./target`. Para acessar o relatório web, abra o seguinte arquivo no seu navegador:

```sh
target/site/jacoco/index.html
```

![image](https://github.com/user-attachments/assets/102db461-6fa9-4294-a2f9-f68884ca4ba9)

## Testes BDD com Cucumber
Este projeto também utiliza o [Cucumber](https://cucumber.io/) para testes BDD (Behavior Driven Development), permitindo que as especificações sejam escritas em um formato legível para os stakeholders não técnicos.

### Estrutura dos Testes
Os cenários de teste estão localizados na pasta `src/test/resources/features`. Cada cenário é definido em arquivos `.feature`, utilizando a linguagem Gherkin. Aqui está um exemplo de um arquivo `.feature`:

```gherkin
Feature: Pedido
  Scenario: Inicializar Pedido Corretamente
    Given que um pedido é inicializado
    Then os atributos do pedido devem ser corretamente definidos

  Scenario: Atualizar Atributos do Pedido
    Given que um pedido é inicializado
    When os atributos do pedido são atualizados
    Then os novos atributos do pedido devem ser corretamente definidos
```

### Executando os Testes BDD
Para rodar os testes BDD com Cucumber, você pode utilizar um dos seguintes métodos:

1. **Via Maven:**
   Na raiz do projeto, execute o comando:

    ```sh
    mvn test
    ```

2. **Diretamente pela Classe do Cucumber:**
   Execute a classe `RunCucumberTest` localizada em `src/test/java/com/fiap/mspedidoapi/bdd`:

Com isso, você pode rodar os testes diretamente pela IDE ou pelo comando Maven, conforme sua preferência.

