package com.example.esr.api.controller;

import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.service.CidadeService;
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
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable Long id) {

       return service.buscarOuFalhar(id);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){

        return service.salvar(cidade);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade atualizar(@PathVariable Long id,
                            @RequestBody Cidade cidade) {

        var cidadeBuscada = service.buscarOuFalhar(id);

        BeanUtils.copyProperties(cidade, cidadeBuscada, "id");

        return service.salvar(cidadeBuscada);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){

        service.excluir(id);

    }

}
