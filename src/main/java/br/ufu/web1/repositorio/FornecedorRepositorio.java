package br.ufu.web1.repositorio;

import br.ufu.web1.dominio.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {
}