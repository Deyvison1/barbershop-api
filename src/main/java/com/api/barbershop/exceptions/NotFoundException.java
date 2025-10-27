package com.api.barbershop.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("Haircut not found with id: " + id);
    }
}
