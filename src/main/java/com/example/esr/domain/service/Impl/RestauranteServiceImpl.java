package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.RestauranteNaoEncontradoException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.RestauranteRepository;
import com.example.esr.domain.service.CozinhaService;
import com.example.esr.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    @Override
    public List<Restaurante> listar() {

        return restauranteRepository.findAll();

    }


    @Override
    public Restaurante salvar(Restaurante restaurante) {

        // Pega o ID da cozinha
        long cozinhaId = restaurante.getCozinha().getId();

        // busca a cozinha pelo ID, se a cozinha não existir lança a exeção CozinhaNaoEncontradaException
        var cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        // Se a cozinha existir ela é adicionada ao restaurante
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    @Override
    public void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

        var servletServerHttpRequest = new ServletServerHttpRequest(request);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            // Falhar caso um propiedad esteja sendo ignorada
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

            // Falhar caso um propiedade não exista
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            //Converte o Json para Java
            Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

            camposOrigem.forEach((k, v) -> {

                //pega somente os campos de restaurante que veio na chave do mapa
                var field = ReflectionUtils.findField(Restaurante.class, k);

                //torna as variaveis de instancia acessivel
                field.setAccessible(true);

                // busca o valor do campo (field)
                var novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                //System.out.println(k +" = "+ v + " = " + novoValor);

                // seta o valor dos campos(field) no restaurante de destino
                ReflectionUtils.setField(field, restauranteDestino, novoValor);

            });

        }catch (IllegalArgumentException ex){

            var rootCause = ExceptionUtils.getRootCause(ex);
            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, servletServerHttpRequest);
        }

    }

    @Override
    public Restaurante buscarOuFalhar(Long id) {

        return restauranteRepository.findById(id)
                .orElseThrow(()-> new RestauranteNaoEncontradoException(id));

    }
}
