package com.paloit.server.petstore.controller;

import com.paloit.server.petstore.api.PetApiDelegate;
import com.paloit.server.petstore.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PetApiControllerImpl implements PetApiDelegate {

    @Override
    public ResponseEntity<Pet> addPet(Pet pet) {
        // TODO: Add implementation...
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO: Override methods from delegate and implement
}
