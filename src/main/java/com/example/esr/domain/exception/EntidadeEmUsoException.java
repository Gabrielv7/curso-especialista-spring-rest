package com.example.esr.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value  = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException{

    public EntidadeEmUsoException(String msg){
        super(msg);
    }

}
