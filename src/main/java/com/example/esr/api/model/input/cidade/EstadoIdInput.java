package com.example.esr.api.model.input.cidade;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdInput {

    @NotNull
    private Long id;
}