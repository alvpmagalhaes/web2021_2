use RH;
create table Empresa(
    cnpj varchar(14) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    cidade varchar(256) not null,
    descricao varchar(256),
    tipoLogin varchar(256),

    primary key (cnpj)
);