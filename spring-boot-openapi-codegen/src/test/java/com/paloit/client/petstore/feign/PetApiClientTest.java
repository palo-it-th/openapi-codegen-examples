package com.paloit.client.petstore.feign;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.paloit.client.petstore.feign.api.PetApiClient;
import com.paloit.client.petstore.feign.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PetApiClientTest {

    @Autowired
    private PetApiClient petApiClient;

    @Test
    public void getPetById_ValidId_200_ReturnPet() throws Exception {
        ResponseEntity<Pet> petResponse = petApiClient.getPetById(1l);
        assertEquals(HttpStatus.OK, petResponse.getStatusCode());

        var pet = petResponse.getBody();

        assertThat(pet.getName()).isNotEmpty();
        assertThat(pet.getId()).isEqualTo(1l);
    }

    // Add further tests...

}
