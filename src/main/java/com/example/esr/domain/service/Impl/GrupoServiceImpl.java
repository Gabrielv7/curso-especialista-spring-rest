package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.GrupoNaoEcontradoException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.model.Grupo;
import com.example.esr.domain.repository.GrupoRepository;
import com.example.esr.domain.service.GrupoService;
import com.example.esr.domain.service.PermissaoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository repository;
    private final PermissaoService permissaoService;

    public static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";

    public GrupoServiceImpl(GrupoRepository repository, PermissaoService permissaoService) {
        this.repository = repository;
        this.permissaoService = permissaoService;
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

    @Transactional
    @Override
    public void associaPermissao(Long grupoId, Long permissaoId) {

        var grupo = this.buscarOuFalhar(grupoId);

        var permissao  = permissaoService.buscarOuFalhar(permissaoId);

        if (grupo.getPermissoes().contains(permissao)){

            throw new NegocioException("Permissão já está associada");

        }

        grupo.getPermissoes().add(permissao);

    }

    @Transactional
    @Override
    public void desassociaPermissao(Long grupoId, Long permissaoId) {

        var grupo = this.buscarOuFalhar(grupoId);

        var permissao  = permissaoService.buscarOuFalhar(permissaoId);

        if (!grupo.getPermissoes().contains(permissao)){

            throw new NegocioException("Permissão já está desassociada");

        }

        grupo.getPermissoes().remove(permissao);

    }

    @Override
    public Grupo buscarOuFalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(()-> new GrupoNaoEcontradoException(id));

    }

}
