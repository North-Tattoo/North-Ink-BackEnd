# North-Ink BackEnd: Configurando e Acessando o Banco de Dados MySQLl
Introdução

Este guia detalha como criar e acessar um banco de dados MySQL em um container Docker nomeado mysql_north_ink.

### Pré-requisitos | Instalação
Docker instalado e em execução em seu sistema: https://docs.docker.com/

### Crie e Inicie o Container MySQL
Abra um terminal no diretório que contém o arquivo docker-compose.yml

Execute o seguinte comando:
- docker-compose up -d

Use o código com cuidado.
A flag -d instrui o Docker a executar o container em modo de fundo, permitindo que ele opere em segundo plano.

Acessando o Banco de Dados

# 1. Usando um Cliente MySQL

Se você possui um cliente MySQL instalado (por exemplo, MySQL Workbench ou cliente mysql da linha de comando), siga estas etapas:

a. Conecte-se ao banco de dados usando o seguinte comando bash:

docker exec -it mysql_north_ink mysql -h localhost -u root -p$MYSQL_ROOT_PASSWORD north-ink

## 2. Ou acesse de outra forma

docker exec -it mysql_north_ink bash

mysql -u root -p$MYSQL_ROOT_PASSWORD


* Substitua $MYSQL_ROOT_PASSWORD pela senha que você definiu para o usuário root.
b. Quando solicitado, digite a senha do usuário root.
