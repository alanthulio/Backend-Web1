package br.ufu.web1.repositorio;

import br.ufu.web1.dominio.Fornecedor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FornecedorRepositorio {

    private final DataSource dataSource;

    public FornecedorRepositorio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Fornecedor save(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            return insert(fornecedor);
        } else {
            return update(fornecedor);
        }
    }

    private Fornecedor insert(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, produto, data, cnpj, contato) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getProduto());

            if (fornecedor.getData() != null) {
                stmt.setDate(3, Date.valueOf(fornecedor.getData()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setString(4, fornecedor.getCnpj());
            stmt.setString(5, fornecedor.getContato());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fornecedor.setId(generatedKeys.getLong(1));
                }
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fornecedor", e);
        }
    }

    private Fornecedor update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome = ?, produto = ?, data = ?, cnpj = ?, contato = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getProduto());

            if (fornecedor.getData() != null) {
                stmt.setDate(3, Date.valueOf(fornecedor.getData()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setString(4, fornecedor.getCnpj());
            stmt.setString(5, fornecedor.getContato());
            stmt.setLong(6, fornecedor.getId());

            stmt.executeUpdate();
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fornecedor", e);
        }
    }

    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT id, nome, produto, data, cnpj, contato FROM fornecedor";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                fornecedores.add(mapRowToFornecedor(rs));
            }
            return fornecedores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fornecedores", e);
        }
    }

    public Optional<Fornecedor> findById(Long id) {
        String sql = "SELECT id, nome, produto, data, cnpj, contato FROM fornecedor WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToFornecedor(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedor por ID", e);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fornecedor", e);
        }
    }

    private Fornecedor mapRowToFornecedor(ResultSet rs) throws SQLException {
        Fornecedor f = new Fornecedor();
        f.setId(rs.getLong("id"));
        f.setNome(rs.getString("nome"));
        f.setProduto(rs.getString("produto"));

        Date dataSql = rs.getDate("data");
        if (dataSql != null) {
            f.setData(dataSql.toLocalDate());
        }

        f.setCnpj(rs.getString("cnpj"));
        f.setContato(rs.getString("contato"));
        return f;
    }
}