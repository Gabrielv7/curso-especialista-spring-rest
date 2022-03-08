package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.FormaDePagamentoNaoEcontradaException;
import com.example.esr.domain.model.FormaPagamento;
import com.example.esr.domain.repository.FormaPagamentoRepository;
import com.example.esr.domain.service.FormaPagamentoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

    private final FormaPagamentoRepository repository;

    public static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso.";

    public FormaPagamentoServiceImpl(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FormaPagamento> listar() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    @Override
    public void excluir(Long id) {

        try {

            repository.deleteById(id);
            repository.flush();

        }catch (EmptyResultDataAccessException ex){

            throw new FormaDePagamentoNaoEcontradaException(id);

        }catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));

        }

    }

    @Override
    public FormaPagamento buscarOuFalhar(Long id){

        return repository.findById(id)
                .orElseThrow(() -> new FormaDePagamentoNaoEcontradaException(id));

    }


}
