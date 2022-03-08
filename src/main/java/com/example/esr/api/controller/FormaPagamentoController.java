package com.example.esr.api.controller;

import com.example.esr.api.mapper.FormaPagamentoMapper;
import com.example.esr.api.model.dto.FormaPagamentoDTO;
import com.example.esr.api.model.input.formaPagamento.FormaPagamentoInput;
import com.example.esr.domain.service.FormaPagamentoService;
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
@RequestMapping("forma-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService service;
    private final FormaPagamentoMapper mapper;

    public FormaPagamentoController(FormaPagamentoService service, FormaPagamentoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<FormaPagamentoDTO> listar(){

        return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamentoDTO buscar(@PathVariable Long id){

        return mapper.toDto(service.buscarOuFalhar(id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput){

        var formaPagamento = mapper.toEntity(formaPagamentoInput);

        return mapper.toDto(service.salvar(formaPagamento));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamentoDTO atualizar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput,
                                       @PathVariable Long id){

        var formaPagamento = service.buscarOuFalhar(id);

        mapper.copyToEntity(formaPagamentoInput, formaPagamento);

        return mapper.toDto(service.salvar(formaPagamento));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){

        service.excluir(id);

    }

}
