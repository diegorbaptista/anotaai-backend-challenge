package com.anotaai.backendchallenge.controllers;

import com.anotaai.backendchallenge.services.CategoryService;
import com.anotaai.backendchallenge.services.dto.CategoryDTO;
import com.anotaai.backendchallenge.services.dto.CreateCategoryDTO;
import com.anotaai.backendchallenge.services.dto.UpdateCategoryDTO;
import com.anotaai.backendchallenge.services.mappers.CategoryMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;
    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CreateCategoryDTO data, UriComponentsBuilder builder) {
        var category = this.service.create(data);
        var uri = builder.path("/api/categories/{categoryId}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.apply(category));
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("categoryId") String categoryId,
                                              @RequestBody @Valid UpdateCategoryDTO data) {
        var category = this.service.update(categoryId, data);
        return ResponseEntity.ok(mapper.apply(category));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
        this.service.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("categoryId") String categoryId) {
        var category = this.service.getById(categoryId);
        return ResponseEntity.ok(mapper.apply(category));
    }
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> list(@PageableDefault() Pageable pagination) {
        var categories = this.service.getAll(pagination);
        return ResponseEntity.ok(categories.map(mapper));
    }
}
