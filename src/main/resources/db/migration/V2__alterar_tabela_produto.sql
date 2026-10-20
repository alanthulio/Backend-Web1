ALTER TABLE produto
    ADD COLUMN tipo_produto VARCHAR(50),
    ADD COLUMN preco NUMERIC(10, 2),
    ADD COLUMN imagem_data BYTEA;