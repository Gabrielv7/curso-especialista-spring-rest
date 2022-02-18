package com.example.esr.api.controller;

import com.example.esr.api.mapper.RestauranteMapper;
import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.api.model.input.RestauranteInput;
import com.example.esr.domain.exception.CozinhaNaoEncontradaException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    private final RestauranteMapper mapper;

    public RestauranteController(RestauranteService service, RestauranteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<RestauranteDTO> listar() {

        return mapper.toCollectionDTO(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO buscar(@PathVariable Long id) {

       var restaurante = service.buscarOuFalhar(id);

       return mapper.toModel(restaurante);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput){

        try {

            var restaurante = mapper.toEntity(restauranteInput);

            return mapper.toModel(service.salvar(restaurante));

        }catch (CozinhaNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO atualizar(@PathVariable Long id,
                                    @RequestBody @Valid RestauranteInput restauranteInput) {

        try {

        var restaurante = service.buscarOuFalhar(id);

         mapper.copyToEntity(restauranteInput, restaurante);

        return mapper.toModel(service.salvar(restaurante));

        }catch (CozinhaNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

/*
    @PatchMapping("/{id}")
    public RestauranteDTO atualizarParcial(@PathVariable Long id,
                                           @RequestBody Map<String, Object> campos,
                                           HttpServletRequest request) {

        Restaurante restauranteAtual = service.buscarOuFalhar(id);

        // faz o merge dos valores que veio no mapa para o restaurante buscado
        service.merge(campos, restauranteAtual, request);

        return this.atualizar(id,restauranteAtual);

    }
*/
}
