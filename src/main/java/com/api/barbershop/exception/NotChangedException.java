package com.api.barbershop.exception;

public class NotChangedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotChangedException() {
        super("Not changed for entity");
    }
}
