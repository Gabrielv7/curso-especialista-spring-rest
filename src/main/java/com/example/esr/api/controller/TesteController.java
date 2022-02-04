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

    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExist(@RequestParam("nome") String nome) {

        return cozinhaRepository.existsByNome(nome);

    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(@RequestParam("taxaInicial") BigDecimal taxaInicial,
                                                      @RequestParam("taxaFinal")BigDecimal taxaFinal) {

        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);

    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(@RequestParam("nome") String nome,
                                                 @RequestParam("cozinhaId")Long id) {

        return restauranteRepository.consultarPorNome(nome, id);

    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePorPrimeiroNome(@RequestParam("nome") String nome) {

        return restauranteRepository.findFirstByNomeContaining(nome);

    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(@RequestParam("nome") String nome) {

        return restauranteRepository.findTop2ByNomeContaining(nome);

    }

    @GetMapping("/restaurantes/count-por-cozinha")
    public int restaurantesCountPorCozinha(@RequestParam("cozinhaId") Long cozinhaId) {

        return restauranteRepository.countByCozinhaId(cozinhaId);

    }

    @GetMapping("/restaurantes/por-nome-frete")    // required = false - Quando o parametro não é óbrigatório para a consulta)
    public List<Restaurante> restaurantesPorNomeFrete(@RequestParam(value = "nome", required = false) String nome,
                                                      @RequestParam(value = "taxaInicial", required = false) BigDecimal taxaInicial,
                                                      @RequestParam(value = "taxaFinal", required = false)BigDecimal taxaFinal) {

        return restauranteRepository.find(nome, taxaInicial, taxaFinal);

    }


}
