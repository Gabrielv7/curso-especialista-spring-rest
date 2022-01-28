package com.example.esr.infrastructure.repository;

import com.example.esr.domain.model.FormaPagamento;
import com.example.esr.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {

        return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();

    }

    @Override
    public FormaPagamento buscar(Long id) {

        return manager.find(FormaPagamento.class,id);

    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Transactional
    @Override
    public void remover(FormaPagamento formaPagamento) {

        formaPagamento = this.buscar(formaPagamento.getId());
        manager.remove(formaPagamento);

    }
}
