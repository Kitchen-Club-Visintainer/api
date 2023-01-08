# Banco de Dados em Container

version: "3.1" versao do compose

**services:** 

>Definindo os serviços que irão rodar junto no container

**db:** 

>Nome do serviço

**image:** _postgres:14.2-alpine_ 
> Aqui nós definimos qual a imamgem do postgres que vamos utilizar no nosso container

**restart:** _always_ 

>definindo estratégia de restart do container


**environment:**
>Definindo as variáveis de ambiente do nosso container.
> Nesse caso, vamos definir usuário e senha do banco de dados.
>> * **POSTGRES_USER é OPCIONAL**. Se nao setar POSTGRES_USER será usado o padrão "postgres"
>> * **POSTGRES_DB é OPCIONAL**. Se nao setar POSTGRES_DB será usado o nome do POSTGRES_USER

**POSTGRES_PASSWORD:** _example_

**ports:**
_- "5432:5432"_

> Estamos expondo a porta 5432 e apontando ela para a porta 5432.
> Dessa forma, podemos acessar esse banco através da porta 5432

* FONTE: https://hub.docker.com/_/postgres
* Versão alpine é a versão mais leve, por isso foi usada no exemplo

## Referências:

Ref: [felipesilvamelo28](https://github.com/felipesilvamelo28/lab-spring-boot-kotlin-basico/tree/main/docker)

* https://hub.docker.com/_/postgres
* https://alpinelinux.org/
* https://medium.com/swlh/alpine-slim-stretch-buster-jessie-bullseye-bookworm-what-are-the-differences-in-docker-62171ed4531d

## Iniciar o container

Para iniciarmos o container com o banco de dados, acessamos o terminal na pasta onde se encontra o `docker-compose.yml`
e entramos com o comando `sudo docker-compose up -d`.

_Obs:_
* _O comando `sudo` é utilizando no linux. Caso esteja rodando em outra plataforma, não é necessário._
* _O comando `-d` servirá para que o container seja criado em background._

Feito isso, o banco de dados estará disponível em `http://localhost:5432`

### pgAdmin

Podemos acessar o banco de dados através da ferramenta pgAdmin 4.

Para isso, vamos adicionar um servidor dentro de um banco de servidores no botão **Add New Server**

Podemos escolher o um nome da nossa escolha. Na aba ***Connection*** temos que colocar um endereço, no nosso caso será _localhost_
Como dafault, já virá configura com a porta 5432, que já configuramos acima.

Não configuramos o username, logo o nosso banco já estará configurado como _postgres_, conforme já vem definido no pgAdmin.
Em PASSWORD, vamos configurar conforme utilizamos em nosso arquivo.

Ao clicar para salvar, o pgAdmin 4 já fará uma conexão com o nosso banco de dados.