package com.example.esr.api.controller;

import com.example.esr.api.mapper.PermissaoMapper;
import com.example.esr.api.model.dto.PermissaoDTO;
import com.example.esr.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    private final GrupoService grupoService;
    private final PermissaoMapper permissaoMapper;


    public GrupoPermissoesController(GrupoService grupoService, PermissaoMapper permissaoMapper) {
        this.grupoService = grupoService;
        this.permissaoMapper = permissaoMapper;
    }

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId){

        var grupo = grupoService.buscarOuFalhar(grupoId);

        var permissoes = grupo.getPermissoes();

        return permissaoMapper.toCollectionDto(permissoes);

    }


    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        grupoService.associaPermissao(grupoId, permissaoId);

    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        grupoService.desassociaPermissao(grupoId, permissaoId);

    }


}
