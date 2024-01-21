package com.anotaai.backendchallenge.service.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Product already exists with its ownerId");
    }
}
