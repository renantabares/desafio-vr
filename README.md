
# mini-autorizador

Os microserviços precisa inicializar em uma determinada sequência: 

1. vr-card (ele é o app responsável por rodar o arquivo compose.yaml) 
2. vr-users (ele é consumido pelo vr-authorization-server para gerar o token jwt)
3. vr-authorization-server

Cada microserviço tem um readme listando os passsos para rodar local. O banco selecionado foi o MongoDB.
na diretório /issets tem a colection do postman utilizada em tempo de desenvolvimento 
