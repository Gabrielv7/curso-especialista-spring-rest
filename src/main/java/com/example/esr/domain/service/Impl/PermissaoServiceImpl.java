package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.PermissaoNaoEcontradaException;
import com.example.esr.domain.model.Permissao;
import com.example.esr.domain.repository.PermissaoRepository;
import com.example.esr.domain.service.PermissaoService;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    private final PermissaoRepository repository;

    public PermissaoServiceImpl(PermissaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Permissao buscarOuFalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(()-> new PermissaoNaoEcontradaException(id));

    }
}
