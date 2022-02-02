package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {

        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();

    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        return manager.merge(cozinha);

    }

    @Override
    public Cozinha buscar (Long id) {


        return manager.find(Cozinha.class,id);

    }

    @Override
    @Transactional
    public void remover (Long id) {

        var cozinha = this.buscar(id);

        if(Objects.isNull(cozinha)) {

            throw new EmptyResultDataAccessException(1);

        }

        manager.remove(cozinha);

    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {

        return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();

    }

}
