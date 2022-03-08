package com.example.esr.api.controller;

import com.example.esr.api.mapper.RestauranteMapper;
import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.api.model.input.restaurente.RestauranteInput;
import com.example.esr.domain.exception.CidadeNaoEncontradaException;
import com.example.esr.domain.exception.CozinhaNaoEncontradaException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.service.RestauranteService;
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

        return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO buscar(@PathVariable Long id) {

       var restaurante = service.buscarOuFalhar(id);

       return mapper.toDto(restaurante);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput){

        try {

            var restaurante = mapper.toEntity(restauranteInput);

            return mapper.toDto(service.salvar(restaurante));

        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException ex){
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

        return mapper.toDto(service.salvar(restaurante));

        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id){

        service.ativar(id);

    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id){

        service.inativar(id);

    }

}
