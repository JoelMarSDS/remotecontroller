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

## Requirements:

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

> GET `/actress/listaActress`

Método para listar atriz.

> GET `/actress/actressId/{byId}`

Método que consulta dados de uma atriz pelo Id. 

> GET `/actress/actressStatus/{byStatus}`

Método que consulta atriz usando status como parâmetro.

##### Parameters:

- **status**: Use 'true' para obter atrizes ativas na plataforma.

##### Exemples: 

` /actress/getActressesByStatus?status=false `

` /actress/getActressesByStatus?status=true `

> PUT `/actress/updateactress{updateactressId}` 

Atualiza dado da atriz por Id. 

> DELETE `/actress/deleteActress/{deleteActressId}` 

Método que deleta atriz usando como paramentro de Id. 

#### ProducerController 

> POST `/producer/create:`

Método dedicado a inserir uma produtor.


> PUT `/producer/updateProducer/{updateProducerId}` 

Atualiza dados do produtor.


> DELETE `/actress/deleteActress/{deleteActressId}` 

Método que deleta atriz usando como paramentro de Id. 

#### ReserveController

> POST `/reserve/saveReserve/{saveReserveId}`

Método dedicado a reservar data apartir de uma ID.

> GET `/reserve/listReserve` 

Método que retorna lista de reservas.

> GET `/reserve/listReserveActress/{reserveActressId}` 

Recurso dedicado a consultar lista de reservas das atrizes através do Id.

> GET `/reserve/listReserveProducer/{reserveProducerId}`

Recurso dedicado a retornar listas de reservas do produtor através do Id. 

> GET `/reserve/countReserveProducer/{countReserveProducerId}`

Método que consulta números de reservas feitas por produtor.

> GET `/reserve/getMoreReservedProducerDates/{producerId}` - 

Recurso para retornar datas reservadas por um produtor.

## Agradecimentos:
Nosso grupo trabalhou arduamente para resolução do desafio, desafio este que só fomos capazes de desenvolver graças a imersão Accademia Accenture. 
Aprendemos neste desafio como aplicar, gerir e desenvolver um projeto em equipe, somando forças para atingir nosso objetivo em comum.
Então nossos agradecimentos ficam aqui registrados à toda equipe Gama Academy + Accenture e gostariámos de complementar que o programa cumpriu com o objetivo proposto de treinar pessoas e prepará-las para o mundo "coder", transformando nossa carreira dentro da Accenture. A melhor empresa multinacional de consultoria de gestão, tecnologia da informação e outsourcing do mundo. 
Tivemos realmente um treinamento intenso e com muito aprendizado e oportunidade de desenvolvimento, fica aqui então registrado nossos mais sinceros agradecimentos! GRATIDÃO :purple_heart:
