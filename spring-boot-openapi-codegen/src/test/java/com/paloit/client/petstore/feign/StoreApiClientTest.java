package com.paloit.client.petstore.feign;

import com.paloit.client.petstore.feign.api.StoreApiClient;
import com.paloit.client.petstore.feign.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StoreApiClientTest {

    @Autowired
    private StoreApiClient storeApiClient;

    @Test
    public void getPetById_ValidId_200_ReturnOrder() throws Exception {
        var orderId = 1l;
        ResponseEntity<Order> petResponse = storeApiClient.getOrderById(orderId);
        assertEquals(HttpStatus.OK, petResponse.getStatusCode());

        var order = petResponse.getBody();

        assertThat(order.getId()).isEqualTo(orderId);
    }

    // Add further tests...

}
