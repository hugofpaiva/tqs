# LAB4 - Multi-layer application testing (with Spring Boot)

## Perguntas

**Identify a couple of examples on the use of AssertJ expressive methods chaining.**

_Expressive methods chaining_ do _AssertJ_ pode ser observado, por exemplo, na classe `EmployeeRestControllerTemplateIT` com:
        
    assertThat(found).extracting(Employee::getName).containsOnly("bob");
    assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");


**Identify an example in which you mock the behavior of the repository (and avoid involving a database).**

Na classe `EmployeeService_UnitTest` é possível verificar _mocks_ do comportamento do repositório com a definição das expectativas da seguinte forma:

    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
    Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());


**What is the difference between standard @Mock and @MockBean?**

Ao usar _@Mock_ é utilizado o _JUnit_ e _Mockito_, permitindo a criação de uma implementação que replica uma interface ou classe com um conjunto respostas de chamadas a funções pré-definidas, de modo a realizar um teste sem a implementação final de uma dependência de uma dada função.

Por outro lado, ao usar _@MockBean_ é possível adicionar objetos _mock_ com as mesmas propriedades descritas anteriormente ao _Spring Application Context_ permitindo, por exemplo, o _mock_ de um _bean_ e portanto a realização de testes de integração.

**What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?**


O ficheiro `application-integrationtest.properties` possui algumas configurações, como as utilizadas para a ligação às base de dados, utilizadas pela aplicação _Spring Boot_. Neste caso, o ficheiro é utilizado através da anotação _@TestPropertySource_ nos testes de integração, servindo como fornecedor das credenciais e informações da base de dados a ser utilizada.




