package com.example.esr.api.controller;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Estado;
import com.example.esr.domain.service.EstadoService;
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
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }


    @GetMapping
    public List<Estado> listar(){

        return service.listar();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id){

        var estado = service.buscar(id);

        if(Objects.nonNull(estado)){

            return ResponseEntity.ok(estado);

        }

            return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){

        return service.salvar(estado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id,
                                            @RequestBody Estado estado){

        var estadoBuscado = service.buscar(id);

        if(Objects.nonNull(estadoBuscado)){

            BeanUtils.copyProperties(estado, estadoBuscado, "id");

            var estadoAtualizado = service.salvar(estadoBuscado);

            return ResponseEntity.ok(estadoAtualizado);

        }

            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){

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
