# LAB2 - Mocking dependencies (for unit testing)

## [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#1)

_Mockito_ é uma _framework_ que permite configurar objetos _mock_ em conjunção com o _JUnit_ de modo a realizar testes unitários.

Um objeto _mock_ é uma implementação que replica uma interface ou classe, permitindo definir o _output_ dos métodos, utilizar _outputs_ _default_ dos métodos ou receber o _output_ da implementação original dos métodos com _spy_. Isto é útil para testar certas funcionalidades de forma isolada para evitar anomalias nos testes derivadas de fatores externos. Estes objetos podem posteriormente ser injetados em classes que dependem dos serviços que estes replicam através de _Inversion of Control_.

## Perguntas

**Consider that we want to verify the AddressResolver#findAddressForLocation, which invokes a remote geocoding service, available in a REST interface, passing the site coordinates. Which is the service to mock?**

Visto que a classe _AddresssResolver_ depende de um serviço externo, o qual lhe retorna todas as informações necessárias para o posteriormente processamento e transformação num objeto de _Address_, o serviço que faz sentido fazer _mock_ é o _remote geocoding service_ que funciona como _REST interface_, sendo necessário fazer _mock_ da _interface ISimpleHttpClient_.


