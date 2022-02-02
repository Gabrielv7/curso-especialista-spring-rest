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

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository){

        this.estadoRepository = estadoRepository;

    }

    @Override
    public List<Estado> listar() {

        return estadoRepository.findAll();

    }

    @Override
    public Estado buscar(Long id) {

        return estadoRepository.findById(id).orElse(null);
    }

    @Override
    public Estado salvar(Estado estado) {

        return estadoRepository.save(estado);

    }

    @Override
    public void excluir(Long id) {

        try{

            estadoRepository.deleteById(id);

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
