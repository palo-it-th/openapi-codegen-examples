package com.paloit.client.petstore.feign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {

  @Value("${swaggerpetstoreopenapi30.security.apiKey.key:}")
  private String apiKeyKey;

  @Bean
  @ConditionalOnProperty(name = "swaggerpetstoreopenapi30.security.apiKey.key")
  public ApiKeyRequestInterceptor apiKeyRequestInterceptor() {
    return new ApiKeyRequestInterceptor("header", "api_key", this.apiKeyKey);
  }

}
