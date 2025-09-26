# mincroserviço vr-authorization-server


Esse é o serviço de authenticação/ autorização (Authentication Server oath2),  Ele demanda que o serviço users esteja rodando para consumir os dados de usuários.  

passos para rodar:

* Ter os recursos necessário
    - Maven Instalado ou IDE de sua preferência 
    
* clonar esse repositório
* fazer checkout na branch develop
* Com Maven
    - acessar o diretório do micro serviço (/vr-authorization-server)
    - ou importar em sua IDE
    - rodar o comando ./mvnw clean install (ou análogo na IDE)
    - ./mvnw spring-boot:run (ou análogo na IDE)
    -  o app estará rodando em http://localhost:8086

    
    lembrando que as ele precisa que o Users esteja rodando.
