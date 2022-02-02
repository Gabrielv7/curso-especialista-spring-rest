package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Estado;
import com.example.esr.domain.repository.EstadoRepository;
import com.example.esr.domain.service.EstadoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository){

        this.estadoRepository = estadoRepository;

    }

    @Override
    public List<Estado> listar() {

        return estadoRepository.listar();

    }

    @Override
    public Estado buscar(Long id) {

        var estado = estadoRepository.buscar(id);

        if(Objects.nonNull(estado)){

            return estado;

        }

        return null;
    }

    @Override
    public Estado salvar(Estado estado) {

        return estadoRepository.salvar(estado);

    }

    @Override
    public void excluir(Long id) {

        try{

            estadoRepository.remover(id);

        }catch (EmptyResultDataAccessException ex){

            throw new EntidadeNaoEncontradaException(
                  String.format("Não existe cadastro de estado com o código %d.", id)
            );

        } catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso.", id)
            );
        }


    }


}
