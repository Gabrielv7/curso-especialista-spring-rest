package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.repository.CidadeRepository;
import com.example.esr.domain.repository.EstadoRepository;
import com.example.esr.domain.service.CidadeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeServiceImpl(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Cidade> listar() {

        return cidadeRepository.listar();

    }

    @Override
    public Cidade buscar(Long id) {

        var cidade = cidadeRepository.buscar(id);

        if(Objects.nonNull(cidade)){

            return cidade;

        }

             return null;
    }

    @Override
    public Cidade salvar(Cidade cidade) {

        // pega o ID do estado
        long idEstado = cidade.getEstado().getId();

        // busca o estado pelo ID
        var estado = estadoRepository.buscar(idEstado);

        // Se o estado não existir lança a exeção EntidadeNaoEncontradaException
        if(Objects.isNull(estado)){

            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com o código %d.", idEstado)
            );

        }

        // Se o estado existir ele é adicionado ao restaurante
        cidade.setEstado(estado);

        return cidadeRepository.salvar(cidade);

    }

    @Override
    public void excluir(Long id) {

        try {

            cidadeRepository.remover(id);

        }catch (EmptyResultDataAccessException ex){

            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cidade com o código %d.", id)
            );

        }catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser excluida, pois está em uso", id)

            );

        }


    }
}
