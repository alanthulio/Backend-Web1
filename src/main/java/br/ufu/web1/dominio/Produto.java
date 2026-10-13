package br.ufu.web1.dominio;

// Importação das anotações da biblioteca Lombok
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok gera automaticamente os métodos set, get
// Lombok gera os construtores com e sem parâmetro automaticamente
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

//
public class Produto {
    private Long id;
    private String nome;
}
