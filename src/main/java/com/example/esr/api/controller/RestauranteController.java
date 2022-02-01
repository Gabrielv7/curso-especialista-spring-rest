package com.example.esr.api.controller;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurante> listar() {

        return service.listar();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {

        var restaurante = service.buscar(id);

        if(Objects.nonNull(restaurante)){

            return ResponseEntity.ok(restaurante);

        }

            return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){

        try {

            var restauranteSalvo = service.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);

        }catch (EntidadeNaoEncontradaException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

    }

}
