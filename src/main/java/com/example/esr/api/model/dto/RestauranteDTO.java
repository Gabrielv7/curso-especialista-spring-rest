package com.example.esr.api.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class RestauranteDTO {

    private Long id;

    private String nome;

    private BigDecimal precoFrete;

    private CozinhaDTO cozinha;

    private Boolean ativo;

}
