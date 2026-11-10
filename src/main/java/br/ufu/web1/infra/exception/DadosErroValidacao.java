package br.ufu.web1.infra.exception;

public record DadosErroValidacao(String campo, String mensagem) {
}