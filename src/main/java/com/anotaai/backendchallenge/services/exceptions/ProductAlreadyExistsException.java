package com.anotaai.backendchallenge.services.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Product already exists with its ownerId");
    }
}
