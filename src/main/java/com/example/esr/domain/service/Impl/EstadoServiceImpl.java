package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EstadoNaoEncontradoException;
import com.example.esr.domain.model.Estado;
import com.example.esr.domain.repository.EstadoRepository;
import com.example.esr.domain.service.EstadoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso.";

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository){

        this.estadoRepository = estadoRepository;

    }

    @Override
    public List<Estado> listar() {

        return estadoRepository.findAll();

    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {

        return estadoRepository.save(estado);

    }

    @Transactional
    @Override
    public void excluir(Long id) {

        try{

            estadoRepository.deleteById(id);
            estadoRepository.flush();

        }catch (EmptyResultDataAccessException ex){

            throw new EstadoNaoEncontradoException(id);

        } catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, id)
            );
        }


    }

    @Override
    public Estado buscarOuFalhar(Long id) {

        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));

    }


}
