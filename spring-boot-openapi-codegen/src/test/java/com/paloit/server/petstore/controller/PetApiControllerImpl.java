package com.paloit.server.petstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PetApiControllerImpl {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPetById_ValidId_ReturnPet() throws Exception {
        var petId = 1;
        this.mockMvc.perform(
                get("/api/v3/pet/{petId}", petId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("available")))
                .andExpect(jsonPath("$.name", is("Bear")));
    }

    @Test
    public void getPetById_InvalidId_NotFound() throws Exception {
        var petId = 300;
        this.mockMvc.perform(
            get("/api/v3/pet/{petId}", petId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    // Add further tests...

}
