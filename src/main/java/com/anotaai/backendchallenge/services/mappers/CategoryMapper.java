package com.anotaai.backendchallenge.services.mappers;

import com.anotaai.backendchallenge.domain.Category;
import com.anotaai.backendchallenge.services.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class CategoryMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(category.getId(), category.getTitle(), category.getDescription(), category.getOwnerId());
    }
}
