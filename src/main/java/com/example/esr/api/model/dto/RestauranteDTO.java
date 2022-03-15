package com.example.esr.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RestauranteDTO {

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private CozinhaDTO cozinha;

    private Boolean ativo;

    private Boolean aberto;

    private EnderecoDTO endereco;

}
