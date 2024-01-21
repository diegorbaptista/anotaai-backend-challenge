package com.anotaai.backendchallenge.services.dto;

import jakarta.validation.constraints.NotNull;
@NotNull
public record UpdateProductDTO(String id, String title, String description, Integer price, String categoryId) {
}
