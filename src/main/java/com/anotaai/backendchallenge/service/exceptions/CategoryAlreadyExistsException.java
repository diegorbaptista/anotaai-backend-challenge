package com.anotaai.backendchallenge.service.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Category already exists with it`s owner");
    }
}
