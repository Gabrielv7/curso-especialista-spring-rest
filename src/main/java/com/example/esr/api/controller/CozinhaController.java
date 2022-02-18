package com.example.esr.api.controller;

import com.example.esr.api.mapper.CozinhaMapper;
import com.example.esr.api.model.dto.CozinhaDTO;
import com.example.esr.api.model.input.cozinha.CozinhaInput;
import com.example.esr.domain.service.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService service;

    private final CozinhaMapper mapper;

    public CozinhaController(CozinhaService cozinhaService, CozinhaMapper mapper) {
        this.service = cozinhaService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CozinhaDTO> listar(){

       return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaDTO buscar (@PathVariable Long id){

        return mapper.toDto(service.buscarOuFalhar(id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){

        var cozinha = mapper.toEntity(cozinhaInput);

         return mapper.toDto(service.salvar(cozinha));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaDTO atualizar(@PathVariable Long id,
                             @RequestBody @Valid CozinhaInput cozinhaInput){

        var cozinhaAtual = service.buscarOuFalhar(id);

        mapper.copyToEntity(cozinhaInput, cozinhaAtual);

        return mapper.toDto(service.salvar(cozinhaAtual));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){

        service.excluir(id);

    }

}
