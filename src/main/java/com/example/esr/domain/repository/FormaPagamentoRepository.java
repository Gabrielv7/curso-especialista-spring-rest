package com.example.esr.domain.repository;

import com.example.esr.domain.model.FormaPagamento;
import com.example.esr.domain.model.Restaurante;

import java.util.List;

public interface FormaPagamentoRepository {

    /**
     * busca uma lista de formas de pagamento
     * @return lista de formas de pagamento
     */
    List<FormaPagamento> listar();

    /**
     * busca uma forma de pagamento pelo ID
     * @param id Identificador da forma de pagamento
     * @return FormaPagamento
     */
    FormaPagamento buscar(Long id);

    /**
     * salva uma forma de pagamento no banco de dados
     * @param formaPagamento
     * @return Forma de pagamento salva
     */
    FormaPagamento salvar(FormaPagamento formaPagamento);

    /**
     * remove uma forma de pagamento do banco de dados
     * @param formaPagamento
     */
     void remover(FormaPagamento formaPagamento);

}
