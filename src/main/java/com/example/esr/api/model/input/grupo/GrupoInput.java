package com.example.esr.api.model.input.grupo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;

}
