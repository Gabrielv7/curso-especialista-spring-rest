package com.example.esr.api.controller;

import com.example.esr.api.mapper.UsuarioMapper;
import com.example.esr.api.model.dto.UsuarioDTO;
import com.example.esr.api.model.input.usuario.UsuarioInput;
import com.example.esr.api.model.input.usuario.UsuarioUpdateInput;
import com.example.esr.api.model.input.usuario.SenhaInput;
import com.example.esr.domain.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UsuarioDTO> listar(){

        return mapper.toCollectionDto(service.listar());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscar(@PathVariable Long id){

        return mapper.toDto(service.buscarOuFalhar(id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioInput usuarioInput){

        var usuario = mapper.toEntity(usuarioInput);

        return mapper.toDto(service.salvar(usuario));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateInput usuarioUpdateInput){

        var usuario = service.buscarOuFalhar(id);

        mapper.copyToEntity(usuarioUpdateInput, usuario);

        return mapper.toDto(service.salvar(usuario));

    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senhaInput){

        service.atualizarSenha(id, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());

    }

}
