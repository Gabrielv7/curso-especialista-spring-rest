package com.example.esr.api.controller;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.service.CidadeService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }


    @GetMapping
    public List<Cidade> listar() {

        return service.listar();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {

        var cidade = service.buscar(id);

        if(Objects.nonNull(cidade)){

            return ResponseEntity.ok(cidade);

        }

            return ResponseEntity.notFound().build();

    }


    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){

        try {

           var ciadeSalva = service.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(ciadeSalva);

        }catch (EntidadeNaoEncontradaException ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                            @RequestBody Cidade cidade) {

        var cidadeBuscada = service.buscar(id);

        try {

            if(Objects.nonNull(cidadeBuscada)){

                BeanUtils.copyProperties(cidade, cidadeBuscada, "id");

                cidadeBuscada = service.salvar(cidadeBuscada);

                return ResponseEntity.ok(cidadeBuscada);

            }
            return ResponseEntity.notFound().build();

        }catch (EntidadeNaoEncontradaException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){

        try {

            service.excluir(id);

            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException ex){

            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException ex){

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

        }

    }

    }
