package com.anotaai.backendchallenge.services.dtos.categories;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UpdateCategoryDTO(String title, String description) {
}
