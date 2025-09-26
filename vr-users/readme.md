# mincroserviço vr-users


Esse é o serviço de gestão de usuários(# vr-users)
  Ele demanda que o serviço vr-card esteja rodando para consumir o bando de dados mongoDB.  

passos para rodar:

* Ter os recursos necessário
    - Maven Instalado ou IDE de sua preferência 
    - Docker Desktop instalado e rodando 
* clonar esse repositório
* fazer checkout na branch develop
* Com Maven
    - acessar o diretório do micro serviço (/vr-users)
    - ou importar em sua IDE
    - rodar o comando ./mvnw clean install (ou análogo na IDE)
    - ./mvnw spring-boot:run (ou análogo na IDE)
    -  o app estará rodando em http://localhost:8084

    

    
    lembrando que as ele precisa que o vr-card esteja rodando.
