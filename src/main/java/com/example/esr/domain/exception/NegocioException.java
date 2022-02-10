package com.example.esr.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value  = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

    public NegocioException(String msg){
        super(msg);
    }

}
