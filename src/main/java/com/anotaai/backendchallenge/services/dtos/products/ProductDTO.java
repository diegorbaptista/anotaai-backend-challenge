package com.anotaai.backendchallenge.services.dtos.products;

public record ProductDTO(String id, String title, String description, Integer price, String ownerId, String categoryId) {
}
