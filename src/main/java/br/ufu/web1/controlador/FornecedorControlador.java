package br.ufu.web1.controlador;

import br.ufu.web1.dominio.Fornecedor;
import br.ufu.web1.dominio.Produto;
import br.ufu.web1.servico.FornecedorServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorControlador {

    private final FornecedorServico fornecedorServico;

    public FornecedorControlador(FornecedorServico fornecedorServico) {
        this.fornecedorServico = fornecedorServico;
    }

    @GetMapping
    public List<Fornecedor> listarFornecedoresC() {
        return fornecedorServico.listarFornecedoresS();
    }

    @PostMapping
    // Estrutura de uma função: visibilidade (public) -- retorno (String) -- nome (nomeFuncao) -- parâmetro
    public Fornecedor cadastrarFornecedorC(@RequestBody Fornecedor novoFornecedorC) {
        Fornecedor fornecedorCadastrado = fornecedorServico.cadastrarFornecedorS(novoFornecedorC);
        return fornecedorCadastrado;
    }

    @DeleteMapping("/{id}")
    public String deletarFornecedorC(@PathVariable Long id){
        String fornecedorDeletado = fornecedorServico.deletarFornecedorS(id);
        return fornecedorDeletado;
    }

    @PutMapping("/{id}")
    public String atualizarFornecedorC(@PathVariable Long id, @RequestBody Fornecedor atualizarFornecedorC){
        String fornecedorAtualizado = fornecedorServico.atualizarFornecedorS(id, atualizarFornecedorC);
        return fornecedorAtualizado;
    }
}
