package br.ufu.web1.servico;

import br.ufu.web1.dominio.Produto;
import br.ufu.web1.repositorio.ProdutoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {

    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoServico(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public List<Produto> listarProdutosS(){

        List<Produto> listaProdutoAlteracao = produtoRepositorio.listarProdutosR();
        return listaProdutoAlteracao;
    }

    public String cadastrarProdutoS (Produto novoProdutoS){
        String produtoCadastrado = produtoRepositorio.cadastrarProdutoR(novoProdutoS);
        return produtoCadastrado;
    }

    public String deletarProdutoS (Long idProduto){
        String produtoDeletado = produtoRepositorio.deletarProdutoR(idProduto);
        return produtoDeletado;
    }

    public String atualizarProdutoS (Long idProduto, Produto produtoAtualizadoS){
        String produtoAtualizado = produtoRepositorio.atualizarProdutoR(idProduto, produtoAtualizadoS);
        return produtoAtualizado;
    }
}
