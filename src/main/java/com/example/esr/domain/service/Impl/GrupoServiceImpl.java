package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.GrupoNaoEcontradoException;
import com.example.esr.domain.model.Grupo;
import com.example.esr.domain.repository.GrupoRepository;
import com.example.esr.domain.service.GrupoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository repository;

    public static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";

    public GrupoServiceImpl(GrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Grupo> listar() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Grupo salvar(Grupo grupo) {

        return repository.save(grupo);

    }

    @Transactional
    @Override
    public void excluir(Long id) {

        try {

            repository.deleteById(id);
            repository.flush();

        }catch (EmptyResultDataAccessException ex){

            throw new GrupoNaoEcontradoException(id);

        }catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));

        }
    }

    @Override
    public Grupo buscarOuFalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(()-> new GrupoNaoEcontradoException(id));

    }

}
