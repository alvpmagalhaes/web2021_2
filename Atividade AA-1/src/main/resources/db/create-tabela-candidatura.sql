use RH;
create table Candidatura(
    dataCandidatura datetime not null,
    cpfProfissional varchar(11) not null,
    codigoVaga bigint not null,
    status varchar(11) not null,
    arquivo varchar(256) not null,

    foreign key (cpfProfissional) references Profissional(cpf),
    foreign key (codigoVaga) references Vaga(codigo),
    primary key (dataCandidatura, cpfProfissional, codigoVaga)
);