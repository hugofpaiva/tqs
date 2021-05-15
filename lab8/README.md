# LAB8 - Continuous integration with Jenkins

### Jenkins

_Jenkins_ é um servidor de automação gratuito. Ajuda a automatizar as partes do desenvolvimento de software relacionadas com a construção, teste e _deploy_, facilitando a integração contínua e _delivery_ contínua.

### Execução do Guião

Foi feito o download do *jenkins.war* e executado com o comando

`java -jar jenkins.war --httpPort=8080`

![./prints/Screenshot_2021-05-11_at_11.44.05.png](./prints/Screenshot_2021-05-11_at_11.44.05.png)

Posteriormente foi acedida a _Web Interface_, configurado o utilizador _Admin_, instalados todos os _plugins_ necessários e configuradas as _tools_:

![./prints/Screenshot_2021-05-11_at_11.46.08.png](./prints/Screenshot_2021-05-11_at_11.46.08.png)

![./prints/Screenshot_2021-05-11_at_11.46.22.png](./prints/Screenshot_2021-05-11_at_11.46.22.png)

Após isto, foi criado um novo _job_, configurando o projeto de uma aula anterior 

![./prints/Screenshot_2021-05-11_at_11.47.09.png](./prints/Screenshot_2021-05-11_at_11.47.09.png)

![./prints/Screenshot_2021-05-11_at_11.51.34.png](./prints/Screenshot_2021-05-11_at_11.51.34.png)

![./prints/Screenshot_2021-05-11_at_11.51.58.png](./prints/Screenshot_2021-05-11_at_11.51.58.png)

Como pedido, foi feita uma _build_ manualmente com sucesso:

![./prints/Screenshot_2021-05-11_at_11.55.08.png](./prints/Screenshot_2021-05-11_at_11.55.08.png)

![./prints/Screenshot_2021-05-11_at_11.55.36.png](./prints/Screenshot_2021-05-11_at_11.55.36.png)

Próximo passo, **configurar a _pipeline_** com um repositório remoto do projeto anterior

![./prints/Screenshot_2021-05-11_at_11.59.13.png](./prints/Screenshot_2021-05-11_at_11.59.13.png)

![./prints/Screenshot_2021-05-11_at_12.00.35.png](./prints/Screenshot_2021-05-11_at_12.00.35.png)

![./prints/Screenshot_2021-05-11_at_12.01.38.png](./prints/Screenshot_2021-05-11_at_12.01.38.png)

O novo ficheiro _Jenkins_ foi adicionado ao _GitHub_

![./prints/Screenshot_2021-05-11_at_12.10.05.png](./prints/Screenshot_2021-05-11_at_12.10.05.png)

A _build_ foi feita com sucesso

![./prints/Screenshot_2021-05-11_at_12.11.16.png](./prints/Screenshot_2021-05-11_at_12.11.16.png)

![./prints/Screenshot_2021-05-11_at_12.11.49.png](./prints/Screenshot_2021-05-11_at_12.11.49.png)

Foi configurada a estratégia de sondagem automatizada para verificar se existem novas alterações no repositório a cada 5 minutos

![./prints/Screenshot_2021-05-11_at_12.13.16.png](./prints/Screenshot_2021-05-11_at_12.13.16.png)

A _pipeline_ foi atualizada com a fase _Install_

![./prints/Screenshot_2021-05-11_at_12.15.23.png](./prints/Screenshot_2021-05-11_at_12.15.23.png)

A _build_ foi feita com sucesso

![./prints/Screenshot_2021-05-11_at_12.16.56.png](./prints/Screenshot_2021-05-11_at_12.16.56.png)

Adicionou-se um teste com o intuito de falha durante a _build_

![./prints/Screenshot_2021-05-11_at_12.28.05.png](./prints/Screenshot_2021-05-11_at_12.28.05.png)

Foi feito _commit_ e esperou-se até às 12:33:43 para verificar se a _build_ automática falha

![./prints/Screenshot_2021-05-11_at_12.31.48.png](./prints/Screenshot_2021-05-11_at_12.31.48.png)

E acabou por falhar:

![./prints/Screenshot_2021-05-11_at_12.33.24.png](./prints/Screenshot_2021-05-11_at_12.33.24.png)

**Posteriormente foi testado a _Blueocean Interface_**

Instalou-se o plugin:

![./prints/Screenshot_2021-05-11_at_12.38.02.png](./prints/Screenshot_2021-05-11_at_12.38.02.png)

E verificou-se a execução:

![./prints/Screenshot_2021-05-11_at_12.38.57.png](./prints/Screenshot_2021-05-11_at_12.38.57.png)

**Foi feito um Docker Container com o Jenkins**

Criou-se uma network

![./prints/Screenshot_2021-05-11_at_12.44.28.png](./prints/Screenshot_2021-05-11_at_12.44.28.png)

Criou-se um _Dockerfile_ que usa a _Jenkins Docker image_ e o plugin do *blueocean* 

![./prints/Screenshot_2021-05-11_at_12.45.51.png](./prints/Screenshot_2021-05-11_at_12.45.51.png)

Foi feita a _build_ da imagem

![./prints/Screenshot_2021-05-11_at_12.46.16.png](./prints/Screenshot_2021-05-11_at_12.46.16.png)

E a execução da mesma com

```jsx
docker run --name jenkins-blueocean --rm --detach \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:1.1
```

![./prints/Screenshot_2021-05-11_at_12.51.09.png](./prints/Screenshot_2021-05-11_at_12.51.09.png)

O servidor passou a estar disponível em http://localhost:8080

![./prints/Screenshot_2021-05-11_at_12.51.55.png](./prints/Screenshot_2021-05-11_at_12.51.55.png)

![./prints/Screenshot_2021-05-11_at_12.52.47.png](./prints/Screenshot_2021-05-11_at_12.52.47.png)

Foi alterado o _Jenkins file_ para refletir o pedido no guião e executar os testes dentro de um _container Docker_

![./prints/Screenshot_2021-05-11_at_12.54.52.png](./prints/Screenshot_2021-05-11_at_12.54.52.png)

Foi criada uma _pipeline_ para testar no _Jenkins_ a correr no _Docker_

![./prints/Screenshot_2021-05-11_at_12.56.06.png](./prints/Screenshot_2021-05-11_at_12.56.06.png)

Foi removido o teste a falhar

![./prints/Screenshot_2021-05-11_at_12.58.18.png](./prints/Screenshot_2021-05-11_at_12.58.18.png)

Por fim, e apesar de diversas tentativas, não foi possível executar a _pipeline_ devido a problemas de certificados do *Docker* (provavelmente devido ao *MacOS*), necessários para obter as imagens. 

![./prints/Screenshot_2021-05-15_at_14.39.46.png](./prints/Screenshot_2021-05-15_at_14.39.46.png)

Uma vez que o estudante usa o sistema operativo _MacOS_ e este não permite criar novos certificados para tentar remediar o problema, o guião deu-se por terminado.

![./prints/Screenshot_2021-05-15_at_14.37.29.png](./prints/Screenshot_2021-05-15_at_14.37.29.png)
