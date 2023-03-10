package com.paloit.client.petstore.webclient;

import static org.assertj.core.api.Assertions.assertThat;

import com.paloit.client.petstore.webclient.api.StoreApi;
import com.paloit.client.petstore.webclient.model.Order;
import com.paloit.client.petstore.webclient.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StoreApiClientTest {

    private StoreApi storeApiClient;

    @Autowired
    StoreApiClientTest(StoreApi storeApiClient) {
        this.storeApiClient = storeApiClient;
    }

    /**
     * Simple webclient call.
     */
    @Test
    public void getOrderById_ValidId_200_ReturnOrder_simple() {
        var orderId = 1l;
        Order order = storeApiClient.getOrderById(orderId).block();

        assertThat(order.getId()).isEqualTo(1l);
    }

    /**
     * webclient call with access to HTTP status.
     */
    @Test
    public void getOrderById_ValidId_200_ReturnOrder_extended() {
        var orderId = 1l;
        Order order = storeApiClient.getOrderByIdRequestCreation(orderId)
            .onStatus(HttpStatus::isError, (response) -> {
                // Handle errors here!
                switch(response.statusCode()) {
                    case INTERNAL_SERVER_ERROR:
                        // Handle with custom exception
                        break;
                    case BAD_REQUEST:
                        // Handle with custom exception
                        break;
                }
                return Mono.error(
                    // Create own custom exceptions
                    new Exception("Something went terribly wrong!")
                );
            })
            // Deserialize
            .bodyToMono(Order.class)
            .block();

        assertThat(order.getId()).isEqualTo(1l);
    }

    // Add further tests...

}
