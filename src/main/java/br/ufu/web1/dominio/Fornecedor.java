package br.ufu.web1.dominio;

// Importação das anotações da biblioteca Lombok

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "fornecedor")

public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome do Fornecedor não pode ser nulo.")
    @Size(min=1, max=50, message = "Nome do Fornecedor - limite mínimo de 1, e máximo de 50 caracteres permitido.")
    private String nome;
    private String produto;
    @NotNull(message = "O CNPJ do Fornecedor não pode ser nulo.")
    @Positive(message = "O CNPJ deve ser um número positivo.")
    @Size(min=14, max=14, message = "O campo deve conter exatamente 14 caracteres.")
    private String cnpj;
    @Positive(message = "O contato deve ser um número positivo.")
    @Size(min=10, max=11, message = "O campo deve conter exatamente 11 caracteres.")
    private String contato;
}
