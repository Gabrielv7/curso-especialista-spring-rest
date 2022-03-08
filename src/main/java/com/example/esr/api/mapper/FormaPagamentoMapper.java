package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.FormaPagamentoDTO;
import com.example.esr.api.model.input.formaPagamento.FormaPagamentoInput;
import com.example.esr.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoMapper {

    private final ModelMapper modelMapper;

    public FormaPagamentoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FormaPagamentoDTO toDto(FormaPagamento formaPagamento){

        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);

    }

    public List<FormaPagamentoDTO> toCollectionDto(List<FormaPagamento> formasPagamento){

        return formasPagamento.stream().map(this::toDto).collect(Collectors.toList());
    }

    public FormaPagamento toEntity (FormaPagamentoInput formasPagamentoInput){

        return modelMapper.map(formasPagamentoInput, FormaPagamento.class);

    }

    public void copyToEntity(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento){

        modelMapper.map(formaPagamentoInput, formaPagamento);

    }

}
