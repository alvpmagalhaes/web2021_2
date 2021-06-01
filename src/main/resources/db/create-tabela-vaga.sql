create table Vaga(
    dataLimite datetime not null,
    cargo varchar(256) not null,
    descricao varchar(256) not null,
    remuneracao varchar(256) not null,
    codigo bigint not null,
    primary key (codigo)
);