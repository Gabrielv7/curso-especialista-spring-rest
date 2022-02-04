package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.queries.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        var criteria = builder.createQuery(Restaurante.class);

        criteria.from(Restaurante.class);


       return manager.createQuery(criteria)
               .getResultList();

    }

}
