package com.anotaai.backendchallenge.services.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Category already exists with it`s owner");
    }
}
