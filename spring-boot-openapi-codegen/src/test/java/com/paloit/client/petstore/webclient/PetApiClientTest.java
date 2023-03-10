package com.paloit.client.petstore.webclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.paloit.client.petstore.webclient.api.PetApi;
import com.paloit.client.petstore.webclient.model.Pet;
import com.paloit.client.petstore.webclient.model.Pet.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PetApiClientTest {

    private PetApi petApiClient;

    @Autowired
    public PetApiClientTest(PetApi petApiClient) {
        this.petApiClient = petApiClient;
    }

    /**
     * Simple webclient call.
     */
    @Test
    public void getPetById_ValidId_200_ReturnPet_simple() {
        var petId = 9999567l;

        this.createPet(petId, "Horse");
        Pet pet = petApiClient.getPetById(petId).block();

        assertThat(pet.getName()).isNotEmpty();
        assertThat(pet.getId()).isEqualTo(petId);
    }

    /**
     * webclient call with access to HTTP status.
     */
    @Test
    public void getPetById_ValidId_200_ReturnPet_extended() {
        var petId = 9999568l;
        createPet(petId, "Horse");
        Pet pet = petApiClient.getPetByIdRequestCreation(petId)
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
            .bodyToMono(Pet.class)
            .block();

        assertThat(pet.getName()).isNotEmpty();
        assertThat(pet.getId()).isEqualTo(petId);
    }

    private void createPet(long petId, String petName) {
        var petToBeCreated = new Pet();
        petToBeCreated.setId(petId);
        petToBeCreated.setName(petName);
        petToBeCreated.setStatus(StatusEnum.AVAILABLE);
        petApiClient.addPetRequestCreation(petToBeCreated).toBodilessEntity().block();
    }

    // Add further tests...

}
