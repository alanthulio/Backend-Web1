package br.ufu.web1.controlador;

import br.ufu.web1.dominio.Produto;
import br.ufu.web1.servico.ProdutoServico;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoControlador {

    private final ProdutoServico produtoServico;

    public ProdutoControlador(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @GetMapping
    public List<Produto> listarProdutosC() {
        return produtoServico.listarProdutosS();
    }

    @PostMapping
    // Estrutura de uma função: visibilidade (public) -- retorno (String) -- nome (nomeFuncao) -- parâmetro
    public Produto cadastrarProdutosC(@RequestBody @Valid Produto novoProdutoC) {
        Produto produtoCadastrado = produtoServico.cadastrarProdutoS(novoProdutoC);
        return produtoCadastrado;
    }

    @DeleteMapping("/{id}")
    public String deletarProdutoC(@PathVariable Long id){
        String produtoDeletado = produtoServico.deletarProdutoS(id);
        return produtoDeletado;
    }

    @PutMapping("/{id}")
    public String atualizarProdutoC(@PathVariable Long id, @RequestBody @Valid Produto atualizarProdutoC){
        String produtoAtualizado = produtoServico.atualizarProdutoS(id, atualizarProdutoC);
        return produtoAtualizado;
    }
}
