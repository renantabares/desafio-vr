# microserviço VCard

Esse é o serviço que contém toda as regras de negócio, por isso nele estão os testes e o swagger. Ele demanda o Docker Desktop instalado  e rodando em seu Sistema operacional para subir o container do banco de dados. Ele possui uma classe de configuração que faz um dump inicial no banco a titulo de teste e agilidade nos dados. Esse serviço está configurado como um resource server do oath 2, sendo assim, ele exige um token no header das requisições gerado pelo micro-serviço vr-authorization-server. 

passos para rodar:

* Ter os recursos necessário
    - Maven Instalado ou IDE de sua preferência 
    - Docker Desktop instalado e rodando 
* clonar esse repositório
* fazer checkout na branch develop
* Com Maven
    - acessar o diretório do micro serviço (/vr-card) ou importar em sua IDE
    - rodar o comando ./mvnw clean install (ou análogo na IDE)
    - ./mvnw spring-boot:run (ou análogo na IDE)
    -  o app estará rodando em http://localhost:8082

    lembrando que as requisições vão precisar do Token JWT no header da requisição.
