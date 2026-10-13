package br.ufu.web1.repositorio;

import br.ufu.web1.dominio.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositorio {

    private final List<Produto> listaProdutos = new ArrayList<>();

    public List<Produto> listarProdutosR(){
        return listaProdutos;
    }

    public String cadastrarProdutoR(Produto novoProdutoS){
        listaProdutos.add(novoProdutoS);
        return "Produto: " + novoProdutoS.getNome() + " cadastrado.";
    }

    public String deletarProdutoR(Long idProduto){
        for (Produto cadaProduto: listaProdutos){
            if (cadaProduto.getId() == idProduto) {
                listaProdutos.remove(cadaProduto);
                return "Produto com Id: " + cadaProduto.getId() + " removido.";
            }
        }
        return "Produto com Id: " + idProduto + " não encontrado.";
    }

    public String atualizarProdutoR (Long idProduto, Produto produtoAtualizadoR){
        for (Produto cadaProduto: listaProdutos){
            if (cadaProduto.getId().equals(idProduto)) {
                produtoAtualizadoR.setId(idProduto);
                int indice = listaProdutos.indexOf(cadaProduto);
                listaProdutos.set(indice, produtoAtualizadoR);
                return "Produto com Id: " + idProduto + " atualizado.";
            }
        }
        return "Produto com Id: " + idProduto + " não encontrado.";
    }
}
