# LAB3 - Acceptance testing with web automation

## [Selenium](https://www.selenium.dev/)

_Selenium_ é uma projeto com diversas ferramentas e librarias que permitem testes e automação em _browsers_.

Através deste projeto é possível controlar um _website_ com uma _API_ em _Java_ (ou outras linguagens). Para utilizar, por exemplo, o _browser Chrome_ é necessário ter instalado o driver específico para o mesmo.

É possível criar uma automação _web_ através do _Selenium IDE Recorder_ de modo a gravar os testes de acordo com o que o utilizador realiza.

Com recurso à extensão do _Selenium_ para o _JUnit 5_ todo o processo de criação, fecho dos _drivers_ e até a obtenção dos mesmos é automatizada utilizando _Dependency Injection_. Caso se queira testar num _browser_ não instalado na máquina, a extensão tem integração com o _Docker_ permitindo os testes através da execução dos _browsers_ em _containers_.

Por fim, a utilização do [_Page object pattern_](https://www.toptal.com/selenium/test-automation-in-selenium-using-page-object-model-and-page-factory) permite uma melhor organização dos códigos dos testes, tornando o código mais fácil de ler.


