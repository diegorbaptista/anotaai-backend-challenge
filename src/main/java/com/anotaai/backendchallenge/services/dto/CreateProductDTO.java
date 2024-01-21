package com.anotaai.backendchallenge.services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@NotNull
public record CreateProductDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String categoryId,
        @NotBlank
        String ownerId,
        @Positive
        Integer price) {
}
