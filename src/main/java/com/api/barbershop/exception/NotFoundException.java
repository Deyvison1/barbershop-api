package com.api.barbershop.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String id) {
        super("Entity not found with id: " + id);
    }
}
