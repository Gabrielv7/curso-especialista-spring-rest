package com.example.esr.api.controller;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.CozinhaRepository;
import com.example.esr.domain.repository.RestauranteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    final CozinhaRepository cozinhaRepository;
    final RestauranteRepository restauranteRepository;

    public TesteController(CozinhaRepository cozinhaRepository, RestauranteRepository restauranteRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }


    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {

        return cozinhaRepository.findListByNomeContaining(nome);

    }


    @GetMapping("/cozinhas/por-nome-unico")
    public Optional<Cozinha> cozinhaPorNomeUnico(@RequestParam("nome") String nome) {

        return cozinhaRepository.findByNome(nome);

    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(@RequestParam("taxaInicial") BigDecimal taxaInicial,
                                                      @RequestParam("taxaFinal")BigDecimal taxaFinal) {

        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);

    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(@RequestParam("nome") String nome,
                                                 @RequestParam("cozinhaId")Long id) {

        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);

    }

}
