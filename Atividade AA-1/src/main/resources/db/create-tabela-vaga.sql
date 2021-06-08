use RH;
create table Vaga(
    dataLimite datetime not null,
    cargo varchar(256) not null,
    descricao varchar(256) not null,
    remuneracao varchar(256) not null,
    codigo bigint not null,
    cnpjEmpresa varchar(14) not null,
    foreign key (cnpjEmpresa) references Empresa(cnpj),
    primary key (codigo)
);