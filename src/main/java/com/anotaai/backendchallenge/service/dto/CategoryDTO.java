package com.anotaai.backendchallenge.service.dto;

public record CategoryDTO(
        String id,
        String title,
        String description,
        String ownerId
) {
}
