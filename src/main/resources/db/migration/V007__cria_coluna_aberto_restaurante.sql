alter table restaurante add column aberto boolean not null;
update restaurante set aberto = false;