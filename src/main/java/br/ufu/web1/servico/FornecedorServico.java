package br.ufu.web1.servico;

import br.ufu.web1.dominio.Fornecedor;
import br.ufu.web1.repositorio.FornecedorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServico {

    private final FornecedorRepositorio fornecedorRepositorio;

    public FornecedorServico(FornecedorRepositorio fornecedorRepositorio) {
        this.fornecedorRepositorio = fornecedorRepositorio;
    }

    public Fornecedor cadastrarFornecedorS(Fornecedor novoFornecedorS) {
        Fornecedor fornecedorCadastrado = fornecedorRepositorio.save(novoFornecedorS);
        return fornecedorCadastrado;
    }

    public List<Fornecedor> listarFornecedoresS() {
        List<Fornecedor> listaFornecedorAlteracao = fornecedorRepositorio.findAll();
        return listaFornecedorAlteracao;
    }

    public String deletarFornecedorS(Long idFornecedor) {
        fornecedorRepositorio.deleteById(idFornecedor);
        String fornecedorDeletado = "Produto com Id: " + idFornecedor + " excluído com sucesso!";
        return fornecedorDeletado;
    }

    public String atualizarFornecedorS(Long idFornecedor, Fornecedor fornecedorAtualizadoS) {
        Optional<Fornecedor> fornecedorAtualizado = fornecedorRepositorio.findById(idFornecedor);
        if (fornecedorAtualizado.isPresent()) {
            fornecedorAtualizadoS.setId(idFornecedor);
            fornecedorRepositorio.save(fornecedorAtualizadoS);
            return "Produto com Id: " + idFornecedor + " atualizado com sucesso";
        }
        return "Produto: " + idFornecedor + " não encontrado.";
    }
}
