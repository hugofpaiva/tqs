# LAB3 - Acceptance testing with web automation

## [Selenium](https://www.selenium.dev/)

_Selenium_ é uma projeto com diversas ferramentas e librarias que permitem testes e automação em _browsers_.


## Testes de Integração

Classes de teste que terminam em "IT" são consideradas classes de testes de integração e portanto não são executadas continuamente com o _lifecycle_ _test_ do _maven_. Estas classes são especialmente utéis quando queremos garantir a integração deste novo código com um serviço real ao invés do _mock_ do mesmo.

Para permitir a execução destes testes, o _plugin maven failsafe_ deve estar configurado e executar _mvn install failsafe:integration-test_ ao invés de _mvn test_ como acontece para os testes normais.


