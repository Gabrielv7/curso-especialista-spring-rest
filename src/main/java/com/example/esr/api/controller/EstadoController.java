package com.example.esr.api.controller;

import com.example.esr.domain.model.Estado;
import com.example.esr.domain.service.EstadoService;
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

import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    public Estado buscar(@PathVariable Long id){

      return service.buscarOuFalhar(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){

        return service.salvar(estado);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estado atualizar(@PathVariable Long id,
                            @RequestBody Estado estado){

        var estadoBuscado = service.buscarOuFalhar(id);

        BeanUtils.copyProperties(estado, estadoBuscado, "id");

        return service.salvar(estadoBuscado);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){

         service.excluir(id);

    }

}
