package com.example.recargas.infrastructure.config;

import com.example.recargas.domain.service.RecargaService;
import com.example.recargas.infrastructure.output.persistence.RecargaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.mapper.RecargaMapper;

import com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuracion BEANS
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RecargaMapper recargaMapper() {
        return new RecargaMapper();
    }

    @Bean
    public RecargaPersistenceAdapter recargaPersistenceAdapter(RecargaRepository recargaRepository, RecargaMapper recargaMapper) {
        return new RecargaPersistenceAdapter(recargaRepository, recargaMapper);
    }

    @Bean
    public RecargaService recargaService(RecargaPersistenceAdapter recargaPersistenceAdapter) {
        return new RecargaService(recargaPersistenceAdapter);
    }

}
