package com.paloit.server.petstore.controller;

import com.paloit.server.petstore.api.PetApiDelegate;
import com.paloit.server.petstore.model.Pet;
import com.paloit.server.petstore.model.Pet.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Implementation is separated from API definition using the delegate pattern.
 */
@Component
public class PetApiControllerImpl implements PetApiDelegate {

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        if(petId == 1l) {
            var pet = new Pet();
            pet.setId(1l);
            pet.setName("Bear");
            pet.setStatus(StatusEnum.AVAILABLE);
            return ResponseEntity.ok(pet);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Pet> addPet(Pet pet) {
        // TODO: Add implementation...
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO: Override methods from delegate and implement
}
