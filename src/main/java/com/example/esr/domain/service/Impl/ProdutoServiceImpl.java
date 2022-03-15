package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.ProdutoNaoEcontradoException;
import com.example.esr.domain.model.Produto;
import com.example.esr.domain.repository.ProdutoRepository;
import com.example.esr.domain.service.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {

        return repository.findById(restauranteId, produtoId)
                .orElseThrow(()-> new ProdutoNaoEcontradoException(produtoId, restauranteId));

    }
}
