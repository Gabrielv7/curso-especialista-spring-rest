package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.CidadeNaoEncontradaException;
import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.repository.CidadeRepository;
import com.example.esr.domain.service.CidadeService;
import com.example.esr.domain.service.EstadoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {
    
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser excluida, pois está em uso";

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    public CidadeServiceImpl(CidadeRepository cidadeRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    @Override
    public List<Cidade> listar() {

        return cidadeRepository.findAll();

    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {

        // pega o ID do estado
        long idEstado = cidade.getEstado().getId();

        // busca o estado pelo ID se o estado não existir lança a exeção EstadoNaoEncontradoException
        var estado = estadoService.buscarOuFalhar(idEstado);

        // Se o estado existir ele é adicionado ao restaurante
        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);

    }

    @Transactional
    @Override
    public void excluir(Long id) {

        try {

            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        }catch (EmptyResultDataAccessException ex){

            throw new CidadeNaoEncontradaException(id);

        }catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, id)

            );

        }

    }

    @Override
    public Cidade buscarOuFalhar(Long id) {

        return cidadeRepository.findById(id)
                .orElseThrow(()-> new CidadeNaoEncontradaException(id));

    }
}
