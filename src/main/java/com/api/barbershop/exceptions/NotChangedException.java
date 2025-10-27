package com.api.barbershop.exceptions;

public class NotChangedException extends RuntimeException {
    public NotChangedException() {
        super("Not changed for entity");
    }
}
