package com.example.esr.jpa;

import com.example.esr.EsrApplication;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(EsrApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var cozinhaRepository  = applicationContext.getBean(CozinhaRepository.class);

        List<Cozinha> cozinhas = cozinhaRepository.listar();

        for( Cozinha cozinha : cozinhas){
            System.out.println(cozinha.getNome());
        }

    }

}
