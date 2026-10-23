CREATE TABLE fornecedor
(
    id      BIGSERIAL primary key,
    cnpj    VARCHAR(14),
    nome    VARCHAR(50) NOT NULL,
    produto VARCHAR(255),
    contato VARCHAR(11)
);