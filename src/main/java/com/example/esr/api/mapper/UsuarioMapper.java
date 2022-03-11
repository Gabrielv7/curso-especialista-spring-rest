package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.UsuarioDTO;
import com.example.esr.api.model.input.usuario.UsuarioInput;
import com.example.esr.api.model.input.usuario.UsuarioUpdateInput;
import com.example.esr.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UsuarioDTO toDto(Usuario usuario){

        return modelMapper.map(usuario, UsuarioDTO.class);

    }

    public List<UsuarioDTO> toCollectionDto(List<Usuario> usuarios){

        return usuarios.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Usuario toEntity(UsuarioInput usuarioInput) {

        return modelMapper.map(usuarioInput, Usuario.class);

    }

    public void copyToEntity(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {

        modelMapper.map(usuarioUpdateInput, usuario);

    }



}
