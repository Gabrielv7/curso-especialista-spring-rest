package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.CozinhaNaoEncontradaException;
import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import com.example.esr.domain.service.CozinhaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não poder ser removida, pois está em uso";

    private final CozinhaRepository cozinhaRepository;

    public CozinhaServiceImpl(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);

    }

    @Override
    public List<Cozinha> listar() {

        return cozinhaRepository.findAll();

    }

    @Transactional
    @Override
    public void excluir(Long id) {

        try {

            cozinhaRepository.deleteById(id);

        }catch (EmptyResultDataAccessException ex){

            throw new CozinhaNaoEncontradaException(id);

        } catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id)
            );

        }

    }

    @Override
    public Cozinha buscarOuFalhar(Long id) {

        return cozinhaRepository.findById(id)
                .orElseThrow(()-> new CozinhaNaoEncontradaException(id));

    }
}
