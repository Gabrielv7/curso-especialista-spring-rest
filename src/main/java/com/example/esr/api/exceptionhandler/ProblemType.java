package com.example.esr.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {


    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Recurso não encontrado");

    private String titulo;
    private String uri;

    ProblemType(String path, String titulo){

        var uriBase = "https://algafood.com.br";

        var uriFinal = new StringBuilder();

        this.uri = uriFinal.append(uriBase).append(path).toString();
        this.titulo = titulo;

    }
}