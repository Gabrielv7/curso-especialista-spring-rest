package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.NegocioException;
import com.example.esr.domain.exception.RestauranteNaoEncontradoException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.RestauranteRepository;
import com.example.esr.domain.service.CidadeService;
import com.example.esr.domain.service.CozinhaService;
import com.example.esr.domain.service.FormaPagamentoService;
import com.example.esr.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;
    private final CidadeService cidadeService;
    private final FormaPagamentoService formaPagamentoService;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, CozinhaService cozinhaService, CidadeService cidadeService, FormaPagamentoService formaPagamentoService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.formaPagamentoService = formaPagamentoService;
    }

    @Override
    public List<Restaurante> listar() {

        return restauranteRepository.findAll()
                                     .stream()
                                     .sorted(Comparator.comparing(Restaurante::getId))
                                     .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {

        // Pega o ID da cozinha
        long cozinhaId = restaurante.getCozinha().getId();

        // Pega o ID da cidade
        long cidadeId = restaurante.getEndereco().getCidade().getId();

        // busca a cozinha pelo ID, se a cozinha n??o existir lan??a a exe????o CozinhaNaoEncontradaException
        var cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        // busca a cidade pelo ID, se a cidade n??o existir lan??a a exe????o CidadeNaoEncontradaException
        var cidade = cidadeService.buscarOuFalhar(cidadeId);

        // Se a cozinha existir ela ?? adicionada ao restaurante
        restaurante.setCozinha(cozinha);

        // Se a  cidade existir ela ?? adicionada ao restaurante
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Override
    public void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

        var servletServerHttpRequest = new ServletServerHttpRequest(request);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            // Falhar caso um propiedad esteja sendo ignorada
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

            // Falhar caso um propiedade n??o exista
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

    @Transactional
    @Override
    public void ativar(Long id) {

        var restaurante = this.buscarOuFalhar(id);

        restaurante.setAtivo(true);

    }

    @Transactional
    @Override
    public void inativar(Long id) {

        var restaurante = this.buscarOuFalhar(id);

        restaurante.setAtivo(false);

    }

    @Transactional
    @Override
    public void desassociarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {

        var restaurante = buscarOuFalhar(idRestaurante);
        var formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);

        if(!restaurante.getFormasPagamento().contains(formaPagamento)){

            throw new NegocioException("Forma de pagamento j?? est?? desassociada.");

        }

        restaurante.getFormasPagamento().remove(formaPagamento);

    }

    @Transactional
    @Override
    public void associarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {

        var restaurante = buscarOuFalhar(idRestaurante);
        var formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);

        if(restaurante.getFormasPagamento().contains(formaPagamento)){

            throw new NegocioException("Forma de pagamento j?? est?? associada.");

        }

        restaurante.getFormasPagamento().add(formaPagamento);

    }

    @Transactional
    @Override
    public void abrir(Long id) {

        var restaurante = this.buscarOuFalhar(id);

        restaurante.setAberto(true);

    }

    @Transactional
    @Override
    public void fechar(Long id) {

        var restaurante = this.buscarOuFalhar(id);

        restaurante.setAberto(false);

    }
}
