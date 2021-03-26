# LAB2 - Mocking dependencies (for unit testing)

## [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#1)

_Mockito_ é uma _framework_ que permite configurar objetos _mock_ em conjunção com o _JUnit_ de modo a realizar testes unitários.

Um objeto _mock_ é uma implementação que replica uma interface ou classe, permitindo definir o _output_ dos métodos (_expectations_), utilizar _outputs_ _default_ dos métodos ou receber o _output_ da implementação original dos métodos através da técnica de _spying_. Isto é extremamente útil para testar certas funcionalidades de forma isolada de modo a evitar anomalias nos testes, derivadas de fatores externos, constante alteração de _outputs_, etc. Estes objetos podem posteriormente ser injetados em classes que dependem dos serviços que estes replicam desde que o príncipio de _Inversion of Control_ esteja a ser aplicado.

## Testes de Integração

Classes de teste que terminam em "IT" são consideradas classes de testes de integração e portanto não são executadas continuamente com o _lifecycle_ _test_ do _maven_. Estas classes são especialmente utéis quando queremos garantir a integração deste novo código com um serviço real ao invés do _mock_ do mesmo.

Para permitir a execução destes testes, o _plugin maven failsafe_ deve estar configurado e executar _mvn install failsafe:integration-test_ ao invés de _mvn test_ como acontece para os testes normais.

## Perguntas

**Consider that we want to verify the AddressResolver#findAddressForLocation, which invokes a remote geocoding service, available in a REST interface, passing the site coordinates. Which is the service to mock?**

Visto que a classe _AddresssResolver_ depende de um serviço externo, o qual lhe retorna todas as informações necessárias para o posteriormente processamento e transformação num objeto de _Address_, o serviço que faz sentido fazer _mock_ é o _remote geocoding service_ que funciona como _REST interface_, sendo necessário fazer _mock_ da _interface ISimpleHttpClient_.


