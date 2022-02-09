create table pedido(
    /*
    Colunas da tabela
    */
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    subtotal DECIMAL(10,2) NOT NULL,
    taxa_frete DECIMAL (10,2) NOT NULL,
    valor_total DECIMAL (10,2) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_confirmacao DATETIME,
    data_cancelamento DATETIME,
    data_entrega DATETIME,
    status VARCHAR(10) NOT NULL,
    /*
    Embeddable Endereço
    */
    endereco_cep varchar(9) NOT NULL ,
    endereco_logradouro varchar(100) NOT NULL ,
    endereco_numero varchar(20) NOT NULL ,
    endereco_complemento varchar(60),
    endereco_bairro varchar(60) NOT NULL ,

    /*
    Chaves Estrangeiras
    */
    forma_pagamento_id BIGINT NOT NULL,
    restaurante_id BIGINT NOT NULL,
    usuario_cliente_id BIGINT NOT NULL,
    endereco_cidade_id bigint(20) NOT NULL ,
    /*
     relacionamentos FK
    */
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id ) references forma_pagamento(id),
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante(id),
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario(id),
    constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade (id)

)engine=InnoDB default charset=utf8;

create table item_pedido(

    /*
     Colunas da tabela
  */
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quantidade smallint(6) NOT NULL ,
    preco_unitario DECIMAL(10,2) NOT NULL ,
    preco_total DECIMAL(10,2) NOT NULL ,
    obeservacao VARCHAR(255),
    /*
    Chaves Estrangeiras
    */
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    /*
    relacionamentos FK
    */
    unique key uk_item_pedido_produto (pedido_id, produto_id),
    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido(id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto(id)

)engine=InnoDB default charset=utf8;