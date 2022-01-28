package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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
    public void remover(Cidade cidade) {

        cidade = this.buscar(cidade.getId());
        manager.remove(cidade);

    }
}
