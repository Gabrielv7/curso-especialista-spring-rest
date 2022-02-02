package com.example.esr.api.controller;

import com.example.esr.domain.repository.CozinhaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teste")
public class TesteController {

    final CozinhaRepository cozinhaRepository;

    public TesteController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    /*
    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {

        return cozinhaRepository.consultarPorNome(nome);

    }
     */
}
