package com.example.esr.api.controller;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
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

    public CozinhaController( CozinhaService cozinhaService) {
        this.service = cozinhaService;
    }

    @GetMapping
    public List<Cozinha> listar(){

       return service.listar();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscar (@PathVariable Long id){

        return service.buscarOuFalhar(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha){

         return service.salvar(cozinha);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha atualizar(@PathVariable Long id,
                             @RequestBody @Valid Cozinha cozinha){

        var cozinhaAtual = service.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return service.salvar(cozinhaAtual);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){

        service.excluir(id);

    }

}
