package com.example.esr.domain.service;

import com.example.esr.domain.model.FormaPagamento;
import com.example.esr.domain.exception.FormaDePagamentoNaoEcontradaException;
import com.example.esr.domain.exception.EntidadeEmUsoException;

import java.util.List;

public interface FormaPagamentoService {

    /**
     * Busca uma lista de formas de pagamentos
     */
    List<FormaPagamento> listar();

    /**
     * Busca uma única forma de pagamento pelo ID
     * @param id
     * @throws FormaDePagamentoNaoEcontradaException
     */
    FormaPagamento buscarOuFalhar(Long id);

    /**
     * Adiciona uma forma de pagamento no banco de dados
     * @param formaPagamento
     * @return forma de pagamento salva
     */
    FormaPagamento salvar(FormaPagamento formaPagamento);

    /**
     * Deleta uma forma de pagamento do banco de dados
     * @param id Identificador da forma de pagamento
     * @throws FormaDePagamentoNaoEcontradaException
     * @throws EntidadeEmUsoException
     */
    void excluir(Long id);

}
