package br.ufu.web1.repositorio;

import br.ufu.web1.dominio.Produto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositorio {

    private final DataSource dataSource;

    public ProdutoRepositorio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            return insert(produto);
        } else {
            return update(produto);
        }
    }

    private Produto insert(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, tipo_produto, preco, imagem_data) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getCategoria());
            stmt.setBigDecimal(4, produto.getPreco());
            stmt.setBytes(5, produto.getImagem());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getLong(1));
                }
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }

    private Produto update(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, tipo_produto = ?, preco = ?, imagem_data = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getCategoria());
            stmt.setBigDecimal(4, produto.getPreco());
            stmt.setBytes(5, produto.getImagem());
            stmt.setLong(6, produto.getId());

            stmt.executeUpdate();
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, tipo_produto, preco, imagem_data FROM produto";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(mapRowToProduto(rs));
            }
            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
    }

    public Optional<Produto> findById(Long id) {
        String sql = "SELECT id, nome, descricao, tipo_produto, preco, imagem_data FROM produto WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToProduto(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }

    private Produto mapRowToProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getLong("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setCategoria(rs.getString("tipo_produto"));
        p.setPreco(rs.getBigDecimal("preco"));
        p.setImagem(rs.getBytes("imagem_data"));
        return p;
    }
}