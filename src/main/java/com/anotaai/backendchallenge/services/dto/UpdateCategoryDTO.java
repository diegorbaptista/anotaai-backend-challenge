package com.anotaai.backendchallenge.services.dto;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UpdateCategoryDTO(String title, String description) {
}
