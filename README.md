# Projeto GAMA ACADEMY + ACCENTURE - DESAFIO FINAL :rocket:
# Team CusCuz com Java :smile:

## Team :facepunch:

 [@DeMesquita](https://github.com/DeMesquita) 
 
 [@SpinnerZ](https://github.com/SpinnerZ)
 
 [@mgsoares2020](https://github.com/mgsoares2020)
 
 [@JoelMarSDS](https://github.com/JoelMarSDS)
 
 [@lucaslfarias](https://github.com/lucaslfarias)
 

## Descrição do projeto :clapper:
> Status do Projeto: <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>

:pushpin: Desenvolvemos um sistema de controle de habilidades e salários de uma emissora de TV, com as seguintes funcionalidades:

:heavy_check_mark: Cadastro de novo perfil de usuário (ator/atriz);

:heavy_check_mark: Login e logout admin (produtor) e de usuário (ator/atriz); (WIP)

:heavy_check_mark: Sistema de busca na qual um admin (produtor) pode visualizar as reservas que realizou;

:heavy_check_mark: Possibilidade de reservar a data de um ator ou atriz e exibição de status;

:heavy_check_mark: Visão do usuário (ator/atriz) mostrando suas datas já reservadas;

:heavy_check_mark: Dashboard de Admin (produtor) mostrando o número de reservas realizadas até o momento, datas com mais
reservas e atores/atrizes mais reservados.


## API :key:

:heavy_check_mark: Utilizamos o spring boot com a seguintes depêndencias: Spring Data JPA, Spring Security, Spring Web.

:heavy_check_mark: Utilizamos o banco de dados H2.

:heavy_check_mark: Utilizamos como ferramenta de gerenciamento de dependencia de projeto o Gradle.

:heavy_check_mark: Para os tratamentos de excessões foi decidido usar uma abordagem com Spring Handler. Foi inserido também uma customização para retornar o status https devido para o momento do erro.

:heavy_check_mark: Na carência de uma equipe dedicada aos tratamento de erros foram feitos testes de funcionalidades sem se aprofundar no tópico.



## Planning :scroll:

:trophy: Desmembramento e priorização de tasks.

:trophy: Durante os dias de desenvolvimento utilizamos ferramentas como github, trello, slack e zoom para para nos auxiliar.

:trophy: Dailys todos os dias, onde discutíamos pendências, dificuldades e estratégias para as próximas tasks. Após a Daily, retornávamos para nossas tasks e quando necessário, fazíamos pair programming.

:trophy: Não definimos um scrum master para semana, todos acompanhávamos o desenvolvimento do projeto.

:trophy: Listagem de todas as tasks na coluna toDo, e desenvolvimento em si: Doing, Inreview e done.

## GitHub :open_file_folder:

Mantivêmos a main como branch principal.
Cada task tinha que ser feita em nova branch, após a conclusão, era aberta a pull Request e pelo menos dois membros da equipe precisavam fazer a review e aprovar o desenvolvimento, assim, podendo mergear para a branch principal.

## Requirements :

Para iniciar a API será necessário ter em sua máquina instalado a versão JDK11 e configurar as variáveis de ambiente. Para instalar e configurar sua máquina o kit JDK11 está disponível no link abaixo:

``
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html``

## Deploy:
Como solicitado no projeto, o deploy será feito via Azure DevOps e está disponível no seguinte link:

```
Belo link
```
## Methods:

#### ActressController

 
> POST `/actress/create`

Método dedicado a criação de uma atriz.

:heavy_check_mark: GET/actress/listaActress - método para listar atriz.

:heavy_check_mark: GET/actress/actressId/{byId} - método que consulta dados de uma atriz pelo Id. Parâmetro obrigatório: actressId - Id.

> GET `/actress/actressStatus/{byStatus}`  

Método que consulta atriz usando status como parâmetro. Parâmetro obrigatório.

##### Parameters:

- **status**: Use 'true' para obter atrizes ativas na plataforma.

##### Exemples: 

</actress/getActressesByStatus?status=false>

</actress/getActressesByStatus?status=true>

:heavy_check_mark: PUT/actress/updateactress{updateactressId} - atualiza dado da atriz por Id. Parâmetro obrigatório: actressId - Id.

:heavy_check_mark: DELETE/actress/deleteActress/{deleteActressId} - método que deleta atriz usando como paramentro de Id. Parâmetro obrigatório: deleteActressId - Id.



#### ProducerController 

:heavy_check_mark: POST/producer/create: método dedicado a inserir uma produtor.


:heavy_check_mark: PUT/producer/updateProducer/{updateProducerId} - atualiza dado(quais?) do produtor. Parâmetro obrigatório: actressId - Id.


:heavy_check_mark: DELETE/actress/deleteActress/{deleteActressId} - método que deleta atriz usando como paramentro de Id. Parâmetro obrigatório: deleteActressId - Id.

#### ReserveController

:heavy_check_mark: POST/reserve/saveReserve/{saveReserveId} - método dedicado a reservar data apartir de uma ID.Parâmetro obrigatório: saveReserveId - Id.

:heavy_check_mark: GET/reserve/listReserve -  método que retorna lista de reservas.

:heavy_check_mark: GET/reserve/listReserveActress/{reserveActressId} - recurso dedicado a consultar lista de reservas das atrizes através do Id. Parâmetro obrigatório: reserveActressId - Id.

:heavy_check_mark: GET/reserve/listReserveProducer/{reserveProducerId} - recurso dedicado a retornar listas de reservas do produtor através do Id. Parâmetro obrigatório: reserveProducerId - Id.
 
:heavy_check_mark: GET/reserve/countReserveProducer/{countReserveProducerId} -  método que consulta números de reservas feitas por produtor utilizando como parâmetro obrigatório countReserveProducerId - Id.

:heavy_check_mark: GET/reserve/getMoreReservedProducerDates/{producerId} - recurso para retornar datas reservadas por um produtor usando como parâmetro producerId.

## Agradecimentos:
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam nibh felis, ultrices eu cursus sit amet, rhoncus quis sem. In massa nisi, interdum in neque ac, aliquet laoreet massa. Vestibulum augue neque, faucibus sed magna non, auctor rhoncus ante. Vivamus dapibus venenatis bibendum. Nullam non ipsum ut nisl bibendum pellentesque sit amet dapibus dui. Donec molestie quam tellus, id vehicula justo tristique non. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam dapibus ac sem et semper. Etiam tincidunt nulla at neque tincidunt, eu scelerisque tellus imperdiet. Sed at fringilla odio. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mollis pharetra nisl quis auctor. Integer quis porttitor erat. In interdum arcu vel malesuada ornare. Ut feugiat, justo a luctus porttitor, justo libero consequat diam, a dapibus nulla erat a quam. Nam facilisis nibh nunc, elementum varius mi scelerisque et.
