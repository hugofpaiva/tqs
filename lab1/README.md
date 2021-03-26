# LAB1 - Unit testing with JUnit 5

## [JUnit 5](https://junit.org/junit5/docs/current/user-guide/#overview)

_Framework_ para desenvolvimento e execução de testes unitários em código _Java_.

Os testes unitários devem ser criados logo ao criar a classe segundo um _Test-driven development_, sendo escrito o código apenas depois dos testes estarem construídos.

Para utilizar o _JUnit_, é necessário acrescentar o _plugin maven-surefire-plugin_ e a dependência _junit-jupiter_ ao _pom.xml_. No _IntelliJ_ podemos utilizar o "sino" junto do nome da classe para gerar a classe de testes automaticamente.

Após a escrita do código de testes, utilizar o _goal maven_ _test_ para testar.

## [JaCoCo](https://www.baeldung.com/jacoco)

Gera relatórios para projetos _Java_ relacionados com a abrangência do código de acordo com o número de linhas de código que são executados durante os testes automáticos. Assim, é possível ter uma métrica que avalia os testes escritos.

Para utilizar o _JaCoCo_, é necessário acrescentar o _plugin jacoco-maven-plugin_ ao _pom.xml_ e, posteriormente, utilizar o _goal maven jacoco_report_ de modo a gerar um relatório em _HTML_ na pasta _target/site/jacoco_ depois de testar o código.

## Perguntas

**Which classes/methods offer less coverage? Are all possible decision branches being covered?**

A classe que ofereceu menos cobertura foi a _CuponEuromillions_ uma vez que não existiam testes para a função _format_ e esta ocupava grande parte do código. A classe _Dip_ apenas não tinha testes para as funções automaticamente implementadas e, a classe _EuromillionsDraw_, possuia dois métodos curtos sem testes.

Segundo o _Jacoco_, na classe _Dip_, com exceção da linha 64, grande parte das _branches_ de decisão, tipicamente _if/else_, foram abrangidas no código não gerado. Já na classe _CuponEuromillions_, a _branch_ relativa ao _for_ no método _format_ não foi abrangida.



