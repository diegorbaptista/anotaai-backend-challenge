package com.anotaai.backendchallenge.services;

import com.anotaai.backendchallenge.domain.Category;
import com.anotaai.backendchallenge.repositories.CategoryRepository;
import com.anotaai.backendchallenge.services.dto.CreateCategoryDTO;
import com.anotaai.backendchallenge.services.dto.UpdateCategoryDTO;
import com.anotaai.backendchallenge.services.exceptions.CategoryAlreadyExistsException;
import com.anotaai.backendchallenge.services.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    private void checkIfCategoryAlreadyExistsByTitleAndOwnerId(String title, String ownerId) {
        if (repository.existsCategoryByTitleAndOwnerId(title, ownerId)) {
            throw new CategoryAlreadyExistsException();
        }
    }

    public Category create(CreateCategoryDTO data) {
        checkIfCategoryAlreadyExistsByTitleAndOwnerId(data.title(), data.ownerId());
        var newCategory = data.toEntity();
        repository.save(newCategory);
        return newCategory;
    }

    public Category update(UpdateCategoryDTO data) {
        var category = this.repository.findById(data.id()).orElseThrow(CategoryNotFoundException::new);
        checkIfCategoryAlreadyExistsByTitleAndOwnerId(data.title(), category.getOwnerId());

        category.modify(data.title(), data.description());
        this.repository.save(category);
        return category;
    }

    public void delete(String id) {
        var category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

    public Category getById(String id) {
        return this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> getByOwnerId(String ownerId) {
        return this.repository.findAllByOwnerId(ownerId);
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }
}
