create table estado(

    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL

)engine=InnoDB default charset=utf8;

insert into estado (nome)
select distinct nome_estado from cidade;

alter table cidade add column estado_id BIGINT NOT NULL;

UPDATE cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

alter table cidade add constraint fk_cidade_estado
    foreign key (estado_id) references estado(id);

alter table cidade drop column nome_estado;

alter table cidade change nome_cidade nome VARCHAR(80) NOT NULL;