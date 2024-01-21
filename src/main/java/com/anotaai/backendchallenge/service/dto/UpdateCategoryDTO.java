package com.anotaai.backendchallenge.service.dto;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UpdateCategoryDTO(String id, String title, String description) {
}
