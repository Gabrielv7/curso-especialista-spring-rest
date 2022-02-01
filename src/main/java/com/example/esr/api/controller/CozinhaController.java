package com.example.esr.api.controller;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<Cozinha> buscar (@PathVariable Long id){

        var cozinha = service.buscar(id);

        if(Objects.nonNull(cozinha)){

            return ResponseEntity.ok(cozinha);

        }

        return  ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){

         return service.salvar(cozinha);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha){

        var cozinhaAtual = service.buscar(id);

        if(Objects.nonNull(cozinhaAtual)) {

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = service.salvar(cozinhaAtual);

            return ResponseEntity.ok(cozinhaAtual);
        }

            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id){

        try {

            service.excluir(id);

            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException ex) {

            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException ex){

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }

    }

}
