package com.paloit.server.petstore.controller;

import com.paloit.server.petstore.api.StoreApiDelegate;
import com.paloit.server.petstore.model.Order;
import com.paloit.server.petstore.model.Pet;
import com.paloit.server.petstore.model.Pet.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StoreApiControllerImpl implements StoreApiDelegate {

    @Override
    public ResponseEntity<Order> getOrderById(Long orderId) {
        if(orderId == 1l) {
            var order = new Order();
            order.setId(1l);
            order.setQuantity(5);
            order.setComplete(false);
            return ResponseEntity.ok(order);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // TODO: Override methods from delegate and implement

}
