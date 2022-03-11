package com.example.esr.api.model.input.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioUpdateInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
