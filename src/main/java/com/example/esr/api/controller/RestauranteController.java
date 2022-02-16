package com.example.esr.api.controller;

import com.example.esr.Groups;
import com.example.esr.domain.exception.CozinhaNaoEncontradaException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurante> listar() {

        return service.listar()
                .stream()
                .sorted(Comparator.comparing(Restaurante::getId))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante buscar(@PathVariable Long id) {

        return service.buscarOuFalhar(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@Validated(Groups.CadastroRestaurante.class) @RequestBody Restaurante restaurante){

        try {
            return service.salvar(restaurante);
        }catch (CozinhaNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizar(@PathVariable Long id,
                                 @RequestBody Restaurante restaurante) {

        try {

        Restaurante restauranteAtual = service.buscarOuFalhar(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento",
                                        "endereco", "dataCadastro", "produtos");

        return service.salvar(restauranteAtual);

        }catch (CozinhaNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }


    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id,
                                        @RequestBody Map<String, Object> campos,
                                        HttpServletRequest request) {

        Restaurante restauranteAtual = service.buscarOuFalhar(id);

        // faz o merge dos valores que veio no mapa para o restaurante buscado
        service.merge(campos, restauranteAtual, request);

        return this.atualizar(id,restauranteAtual);

    }



}
