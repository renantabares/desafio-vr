# Bem vindo ao repositório do desafio técnico "mini-autorizador"

<p>fazendo a leitura do enunciado, percebi que se trata de uma arquiterura orientada à micro-serviços. Como a proposta deixa bastante claro o que fazer, também  deixa livre para a tomada e decisão em relação  à como fazer. </p>
<p>Sendo assim tomei a liberdade de criar três micro serviços utilizando Spring boot na sua versão 3.1.6 e java 17 </p>
<img src="./microservicos.png" >

<p>Os serviços de <a href="https://github.com/renantabares/desafio-vr/tree/develop/vr-authorization-server"><b>authorization</b></a> e <a href="vr-users"><b>users</b></a> se comunicam para fornecer o tokenJWT, decisão tomada para atuhenticação/autorização, pois na minha visão fica mais aderente à arquitetura em micro serviços. O serviço de <a href="https://github.com/renantabares/desafio-vr/tree/develop/vr-card"><b> Card </b></a>desempenha o papel do resource Server nesse contexto</p>
