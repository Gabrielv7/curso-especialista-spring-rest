package com.example.esr.api.controller;

import com.example.esr.api.mapper.CidadeMapper;
import com.example.esr.api.model.dto.CidadeDTO;
import com.example.esr.api.model.input.cidade.CidadeInput;
import com.example.esr.domain.exception.EstadoNaoEncontradoException;
import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService service;

    private final CidadeMapper mapper;

    public CidadeController(CidadeService service, CidadeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public List<CidadeDTO> listar() {

        return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CidadeDTO buscar(@PathVariable Long id) {

       return mapper.toDto(service.buscarOuFalhar(id));

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput){

        try {

            var cidade = mapper.toEntity(cidadeInput);

            return mapper.toDto(service.salvar(cidade));

        }catch (EstadoNaoEncontradoException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CidadeDTO atualizar(@PathVariable Long id,
                            @RequestBody @Valid CidadeInput cidadeInput) {

        try {

            var cidadeBuscada = service.buscarOuFalhar(id);

            mapper.copyToEntity(cidadeInput, cidadeBuscada);

            return mapper.toDto(service.salvar(cidadeBuscada));

        }catch (EstadoNaoEncontradoException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){

        service.excluir(id);

    }


}
