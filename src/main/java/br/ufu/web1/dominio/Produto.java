package br.ufu.web1.dominio;

// Importação das anotações da biblioteca Lombok

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;

// Lombok gera automaticamente os métodos set, get
// Lombok gera os construtores com e sem parâmetro automaticamente
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
//
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome do Produto não pode ser nulo.")
    @Size(min=1, max=50, message = "Nome do Produto - limite mínimo de 1, e máximo de 50 caracteres permitido.")
    private String nome;
    private String descricao;
    @Column(name = "tipo_produto")
    @NotNull(message = "Categoria do Produto não pode ser nulo.")
    @Size(min=1, max=50, message = "Categoria do Produto - limite mínimo de 1, e máximo de 50 caracteres permitido.")
    private String categoria;
    @NotNull(message = "O preço do Produto não pode ser nulo.")
    @Positive(message = "O preço do Produto deve ser maior que zero.")
    private BigDecimal preco;
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "imagem_data")
    @NotNull(message = "A imagem do Produto não pode ser nula.")
    private byte[] imagem;
}
