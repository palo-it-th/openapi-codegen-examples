package com.paloit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Webclient config used in generated clients
 */
@Slf4j
@Configuration
public class WebclientConfig {

    private final ObjectMapper objectMapper;

    /**
     * Autowire Jackson object mapper instance from the JacksonConfig using constructor injection
     * @param serverMapper objectmapper defined in JacksonConfig.
     */
    public WebclientConfig(ObjectMapper serverMapper) {
        this.objectMapper = serverMapper;
    }

    /**
     * Create a new webclient bean instance.
     *
     * @param webClientBuilder the webclient builder
     * @return webclient bean
     */
    @Bean
    public WebClient generalWebClient(WebClient.Builder webClientBuilder) {
        return WebClient.builder()
            .build();
    }
}
