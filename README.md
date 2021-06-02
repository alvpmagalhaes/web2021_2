## Sobre

Projeto 1 

## Equipe

#####***Alvaro Magalhães***

#####***Jhony Ramos***

#####***Pedro Apolloni***

## Documentação
  
Essa aplicação tem como objetivo gerenciar vagas e candidaturas de profissionais em empresas.

## Requisitos

The main dependencies of the project are:

| Technology | version |
| --- | --- |
| Mysql | latest |
| Maven | 4.0.0 |
| JDK | 1.8 |
|Tomcat |9  |

## Gerar Banco de dados

Vá na pasta src/main/resources/db e execute os arquivos sqls na seguinte ordem:

1 - create-tabela-profissional.sql

2 - create-tabela-empresa.sql

3 - create-tabela-vaga.sql

4 - create-tabela-candidatura.sql

Obs: Banco de dados = RH, usuario = root e sem senha

## Iniciando o projeto

Vá para a pasta do projeto e execute o seguinte commando:

```bash
mvn clean install tomcat7:run
```

