package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.queries.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        var criteria = builder.createQuery(Restaurante.class);

        var root =criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(nome)){

            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));

        }

        if(Objects.nonNull(taxaInicial)){

            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));

        }

        if(Objects.nonNull(taxaFinal)){

            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));

        }

        criteria.where(predicates.toArray(new Predicate[0]));

       return manager.createQuery(criteria)
               .getResultList();

    }

}
