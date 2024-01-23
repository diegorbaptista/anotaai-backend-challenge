package com.anotaai.backendchallenge.services.dto;

import jakarta.validation.constraints.NotNull;
@NotNull
public record UpdateProductDTO(String title, String description, Integer price, String categoryId) {
}
