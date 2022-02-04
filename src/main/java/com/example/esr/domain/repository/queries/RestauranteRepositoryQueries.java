package com.example.esr.domain.repository.queries;

import com.example.esr.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;
// Interface para Queries customizadas
public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
