package com.paloit.server.petstore.controller;

import com.paloit.util.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

public class PetApiControllerImplTest extends BaseTest {

    @Test
    public void getPetById_ValidId_ReturnPet() throws Exception {
        var petId = 1;

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(PATH_PREFIX + "/pet/{petId}", petId)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(1))
            .body("status", equalTo("available"))
            .body("name", equalTo("Bear"));
    }

    @Test
    public void getPetById_InvalidId_NotFound() throws Exception {
        var petId = 300;

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(PATH_PREFIX + "/pet/{petId}", petId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Add further tests...

}
