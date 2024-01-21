package com.anotaai.backendchallenge.services;

import com.anotaai.backendchallenge.domain.Category;
import com.anotaai.backendchallenge.repositories.CategoryRepository;
import com.anotaai.backendchallenge.services.dto.CategoryDTO;
import com.anotaai.backendchallenge.services.dto.CreateCategoryDTO;
import com.anotaai.backendchallenge.services.dto.UpdateCategoryDTO;
import com.anotaai.backendchallenge.services.exceptions.CategoryAlreadyExistsException;
import com.anotaai.backendchallenge.services.exceptions.CategoryNotFoundException;
import com.anotaai.backendchallenge.services.mappers.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private void checkIfCategoryAlreadyExistsByTitleAndOwnerId(String title, String ownerId) {
        if (repository.existsCategoryByTitleAndOwnerId(title, ownerId)) {
            throw new CategoryAlreadyExistsException();
        }
    }

    public CategoryDTO create(CreateCategoryDTO data) {
        checkIfCategoryAlreadyExistsByTitleAndOwnerId(data.title(), data.ownerId());
        var newCategory = data.toEntity();
        repository.save(newCategory);
        return this.mapper.apply(newCategory);
    }

    public CategoryDTO update(String id, UpdateCategoryDTO data) {
        var category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        checkIfCategoryAlreadyExistsByTitleAndOwnerId(data.title(), category.getOwnerId());

        category.modify(data.title(), data.description());
        this.repository.save(category);
        return this.mapper.apply(category);
    }

    public void delete(String id) {
        var category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

    public CategoryDTO getById(String id) {
        return this.repository.findById(id)
                .map(mapper)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public List<CategoryDTO> getByOwnerId(String ownerId) {
        return this.repository.findAllByOwnerId(ownerId)
                .stream()
                .map(mapper)
                .toList();
    }

    public Page<CategoryDTO> getAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(mapper);
    }
}
