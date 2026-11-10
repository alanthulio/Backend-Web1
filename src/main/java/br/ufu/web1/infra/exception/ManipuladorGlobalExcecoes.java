package br.ufu.web1.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ManipuladorGlobalExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErrosValidacao(MethodArgumentNotValidException excecao){
        List<DadosErroValidacao> listaErro = new ArrayList<>();
        for (FieldError erro : excecao.getFieldErrors()){
            DadosErroValidacao erroFormatado = new DadosErroValidacao(erro.getField(), erro.getDefaultMessage());
            listaErro.add(erroFormatado);
        }
        return ResponseEntity.badRequest().body(listaErro);
    }

}
