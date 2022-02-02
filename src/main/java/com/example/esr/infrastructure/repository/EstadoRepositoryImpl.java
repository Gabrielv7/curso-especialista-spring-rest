package com.example.esr.infrastructure.repository;

import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Estado;
import com.example.esr.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Estado> listar() {

        return manager.createQuery("from Estado", Estado.class).getResultList();

    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long id) {

       var estado = this.buscar(id);

       if(Objects.isNull(estado)){

           throw new EmptyResultDataAccessException(1);

       }

            manager.remove(estado);

    }
}
