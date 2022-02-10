package com.example.esr.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Problem {

    private LocalDateTime dataHora;
    private String mensagem;

}
