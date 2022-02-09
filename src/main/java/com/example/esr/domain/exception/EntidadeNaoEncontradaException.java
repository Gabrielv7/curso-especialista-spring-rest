package com.example.esr.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value  = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

    public EntidadeNaoEncontradaException(HttpStatus status, String msg) {
        super(status, msg);
    }

    public EntidadeNaoEncontradaException(String msg){
        this(HttpStatus.NOT_FOUND ,msg);
    }

}
