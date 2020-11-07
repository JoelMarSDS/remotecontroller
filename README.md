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

:heavy_check_mark: No quesito teste para Deloper foram tratados as excessões.


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

Para iniciar a API será necessário ter em sua máquina instalado a versão JDK11 em sua máquina e configurar as variáveis de ambiente. Para configurar as variáveis basta colar no seu prompt de comando o link abaixo:

```
setx -m JAVA_HOME "C:\Program Files\Java\jdk-xx.x.x"
```

## Deploy:
Como solicitado no projeto, o deploy será feito via Azure DevOps e está disponível no seguinte link:

```
Belo link
```
## methods:

POST/actress/create: metodo dedicado a inserir uma atriz.
GET/actress/listaActress- metodo que lista atriz.
GET/actress//actressId/{byId}- metodo que lista atriz. Parametro obrigatório: actressId - Id.
GET/actress//actressStatus/{byStatus} - metodo que lista atriz. Parametro obrigatório: actressStatus - Status.
PUT/actress/updateactress{updateactressId} - atualiza dado da atriz. Parametro obrigatório: actressId - Id.

Exemple: /actress/getActressesByStatus?status=false
         /actress/getActressesByStatus?status=true


## Agradecimentos:
Gostariamos de agradecer primeiramente a Accenture e a Gama por nos ter desafiado durante todo processo, pois sem as mesmas este projeto não existiriam. Queremos agradecer, também, nossos parentes e amigos por nos ter dado todo suporte neste ultimo e intenso mês.(piegas pra caramba)
