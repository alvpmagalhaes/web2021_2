create database RH;

use RH;

create table Profissional(
    cpf varchar(11) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    sexo varchar(256) not null,
    telefone varchar(13) not null,
    dataNascimento date not null
    primary key (cpf)
);