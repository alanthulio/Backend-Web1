create table produto
(
    id        BIGSERIAL primary key,
    nome      VARCHAR(50) NOT NULL,
    descricao VARCHAR(255)
);