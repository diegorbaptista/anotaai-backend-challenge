package com.anotaai.backendchallenge.service.dto;

import com.anotaai.backendchallenge.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
public record CreateCategoryDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String ownerId
) {
    public Category toEntity() {
        return new Category("", title, description, ownerId);
    }
}
