package com.anotaai.backendchallenge.service.dto;

import jakarta.validation.constraints.NotNull;
@NotNull
public record UpdateProductDTO(String id, String title, String description, Integer price, String categoryId) {
}
