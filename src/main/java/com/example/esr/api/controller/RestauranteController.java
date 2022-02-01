package com.example.esr.api.controller;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
