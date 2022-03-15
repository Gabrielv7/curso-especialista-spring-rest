package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.ProdutoDTO;
import com.example.esr.api.model.input.produto.ProdutoInput;
import com.example.esr.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    private final ModelMapper modelMapper;

    public ProdutoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoDTO toDto(Produto produto){

        return modelMapper.map(produto, ProdutoDTO.class);

    }

    public List<ProdutoDTO> toCollectionDto(List<Produto> produtos){

        return produtos.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Produto toEntity(ProdutoInput produtoInput){

        return modelMapper.map(produtoInput, Produto.class);

    }

    public void copyToEntity(ProdutoInput produtoInput, Produto produto){

        modelMapper.map(produtoInput, produto);

    }

}
