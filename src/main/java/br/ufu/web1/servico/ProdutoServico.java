package br.ufu.web1.servico;

import br.ufu.web1.dominio.Produto;
import br.ufu.web1.repositorio.ProdutoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoServico(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public Produto cadastrarProdutoS(Produto novoProdutoS) {
        Produto produtoCadastrado = produtoRepositorio.save(novoProdutoS);
        return produtoCadastrado;
    }

    public List<Produto> listarProdutosS() {
        List<Produto> listaProdutoAlteracao = produtoRepositorio.findAll();
        return listaProdutoAlteracao;
    }

    public String deletarProdutoS(Long idProduto) {
        produtoRepositorio.deleteById(idProduto);
        String produtoDeletado = "Produto com Id: " + idProduto + " excluído com sucesso!";
        return produtoDeletado;
    }

    public String atualizarProdutoS(Long idProduto, Produto produtoAtualizadoS) {
        Optional<Produto> produtoAtualizado = produtoRepositorio.findById(idProduto);
        if (produtoAtualizado.isPresent()) {
            produtoAtualizadoS.setId(idProduto);
            produtoRepositorio.save(produtoAtualizadoS);
            return "Produto com Id: " + idProduto + " atualizado com sucesso";
        }
        return "Produto: " + idProduto + " não encontrado.";
    }
}
