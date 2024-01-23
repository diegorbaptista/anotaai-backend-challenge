package com.anotaai.backendchallenge.services.dtos.categories;

public record CategoryDTO(
        String id,
        String title,
        String description,
        String ownerId
) {
}
