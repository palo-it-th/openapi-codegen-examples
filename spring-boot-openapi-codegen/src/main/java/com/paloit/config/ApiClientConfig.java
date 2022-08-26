package com.paloit.config;

import com.paloit.client.petstore.webclient.api.PetApi;
import com.paloit.client.petstore.webclient.api.StoreApi;
import com.paloit.client.petstore.webclient.api.UserApi;
import com.paloit.client.petstore.webclient.apiclient.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for generated clients (webclient). Any new client needs to be registered here.
 * All clients use a common webclient instance defined in the WebclientConfig.
 *
 * Authorization can be added with new ApiClient instances.
 */
@Slf4j
@Configuration
public class ApiClientConfig {

    private final WebClient webclient;
    private final String petstoreBasePath;

    /**
     * Autowires property from the application.yml to set as a base path for the below api clients and a webclient instance
     * defined in the WebclientConfig.
     *
     * @param webclient global webclient
     * @param petstoreBasePath base path for the clients defined below
     */
    public ApiClientConfig(
        WebClient webclient,
        @Value("${client.petstore.base-path}") String petstoreBasePath
    ) {
        this.webclient = webclient;
        this.petstoreBasePath = petstoreBasePath;
    }

    /**
     * Client for the pet api.
     * @return pet api
     */
    @Bean
    public PetApi petClient() {
        return new PetApi(
            new ApiClient(webclient).setBasePath(petstoreBasePath)
        );
    }

    /**
     * Client for the store api.
     * @return store api
     */
    @Bean
    public StoreApi storeClient() {
        return new StoreApi(
            new ApiClient(webclient).setBasePath(petstoreBasePath)
        );
    }

    /**
     * Clienat for the user api.
     *
     * @return user api
     */
    @Bean
    public UserApi userClient() {
        return new UserApi(
            new ApiClient(webclient).setBasePath(petstoreBasePath)
        );
    }

}
