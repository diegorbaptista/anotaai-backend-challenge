package com.anotaai.backendchallenge.controllers;

import com.anotaai.backendchallenge.services.CategoryService;
import com.anotaai.backendchallenge.services.dto.CategoryDTO;
import com.anotaai.backendchallenge.services.dto.CreateCategoryDTO;
import com.anotaai.backendchallenge.services.dto.UpdateCategoryDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CreateCategoryDTO data, UriComponentsBuilder builder) {
        var categoryDTO = this.service.create(data);
        var uri = builder.path("/api/categories/{categoryId}").buildAndExpand(categoryDTO.id()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("categoryId") String categoryId,
                                              @RequestBody @Valid UpdateCategoryDTO data) {
        var categoryDTO = this.service.update(categoryId, data);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
        this.service.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("categoryId") String categoryId) {
        var categoryDTO = this.service.getById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> list(@PageableDefault() Pageable pagination) {
        var categories = this.service.getAll(pagination);
        return ResponseEntity.ok(categories);
    }

}
