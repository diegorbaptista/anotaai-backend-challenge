package com.anotaai.backendchallenge.services.dto;

public record CategoryDTO(
        String id,
        String title,
        String description,
        String ownerId
) {
}
