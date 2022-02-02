package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Cidade> listar() {

        return manager.createQuery("from Cidade", Cidade.class).getResultList();

    }

    @Override
    public Cidade buscar(Long id) {

        return manager.find(Cidade.class, id);

    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {

        return manager.merge(cidade);

    }

    @Transactional
    @Override
    public void remover(Long id) {

        var cidade = this.buscar(id);

        if(Objects.isNull(cidade)){

            throw new EmptyResultDataAccessException(1);

        }

            manager.remove(cidade);

    }
}
