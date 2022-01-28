package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
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
    public void remover (Cozinha cozinha) {

        cozinha = this.buscar(cozinha.getId());
        manager.remove(cozinha);

    }
}
