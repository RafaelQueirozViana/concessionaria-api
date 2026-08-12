package com.concessionaria.exception;

import com.concessionaria.dto.ErroCampoDTO;
import com.concessionaria.dto.ErroDTO;
import com.concessionaria.dto.ErroValidacaoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoDTO> tratarValidacao(MethodArgumentNotValidException ex) {
        List<ErroCampoDTO> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> new ErroCampoDTO(erro.getField(), erro.getDefaultMessage()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroValidacaoDTO(HttpStatus.BAD_REQUEST.value(), erros));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroDTO> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroDTO> tratarDuplicado(RegistroDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErroDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroDTO> tratarParametroInvalido(MethodArgumentTypeMismatchException ex) {
        String mensagem = "o parâmetro '" + ex.getName() + "' está em um formato inválido";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroDTO(HttpStatus.BAD_REQUEST.value(), mensagem));
    }


}