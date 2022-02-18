package com.example.esr.api.controller;

import com.example.esr.api.mapper.EstadoMapper;
import com.example.esr.api.model.dto.EstadoDTO;
import com.example.esr.api.model.input.estado.EstadoInput;
import com.example.esr.domain.service.EstadoService;
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
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService service;

    private final EstadoMapper mapper;

    public EstadoController(EstadoService service, EstadoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public List<EstadoDTO> listar(){

        return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoDTO buscar(@PathVariable Long id){

      return mapper.toDto(service.buscarOuFalhar(id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar(@RequestBody @Valid EstadoInput estadoInput){

        var estado = mapper.toEntity(estadoInput);

        return mapper.toDto(service.salvar(estado));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoDTO atualizar(@PathVariable Long id,
                            @RequestBody @Valid EstadoInput estadoInput){

        var estadoBuscado = service.buscarOuFalhar(id);

       mapper.copyToEntity(estadoInput, estadoBuscado);

        return mapper.toDto(service.salvar(estadoBuscado));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){

         service.excluir(id);

    }

}
