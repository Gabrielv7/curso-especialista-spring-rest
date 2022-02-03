package com.example.esr.api.controller;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    final CozinhaRepository cozinhaRepository;

    public TesteController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }


    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {

        return cozinhaRepository.findListByNome(nome);

    }


    @GetMapping("/cozinhas/por-nome-unico")
    public Optional<Cozinha> cozinhaPorNomeUnico(@RequestParam("nome") String nome) {

        return cozinhaRepository.findByNome(nome);

    }

}
