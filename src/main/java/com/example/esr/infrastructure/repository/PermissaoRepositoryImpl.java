package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Permissao;
import com.example.esr.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Permissao> listar() {

        return manager.createQuery("from Permissao",Permissao.class).getResultList();

    }

    @Override
    public Permissao buscar(Long id) {

        return manager.find(Permissao.class, id);

    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {

        return manager.merge(permissao);

    }

    @Transactional
    @Override
    public void remover(Permissao permissao) {

        permissao = this.buscar(permissao.getId());
        manager.remove(permissao);

    }
}
