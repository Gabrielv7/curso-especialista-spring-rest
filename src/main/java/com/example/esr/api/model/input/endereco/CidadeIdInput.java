package com.example.esr.api.model.input.endereco;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeIdInput {

    @NotNull
    private Long id;

}
