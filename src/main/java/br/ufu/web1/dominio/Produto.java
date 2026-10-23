package br.ufu.web1.dominio;

// Importação das anotações da biblioteca Lombok

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String nome;
    private String descricao;
    @Column(name = "tipo_produto")
    private String tipo;
    private BigDecimal preco;
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "imagem_data")
    private byte[] imagem;
}
