package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Restaurante> listar() {

        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();

    }

    @Override
    public Restaurante buscar(Long id) {

        return manager.find(Restaurante.class, id);

    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {

        return manager.merge(restaurante);

    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {

        restaurante = this.buscar(restaurante.getId());
        manager.remove(restaurante);

    }
}
