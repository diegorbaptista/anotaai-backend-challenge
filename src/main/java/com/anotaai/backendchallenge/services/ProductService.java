package com.anotaai.backendchallenge.services;

import com.anotaai.backendchallenge.domain.Category;
import com.anotaai.backendchallenge.domain.Product;
import com.anotaai.backendchallenge.repositories.ProductRepository;
import com.anotaai.backendchallenge.services.dto.CreateProductDTO;
import com.anotaai.backendchallenge.services.dto.UpdateProductDTO;
import com.anotaai.backendchallenge.services.exceptions.ProductAlreadyExistsException;
import com.anotaai.backendchallenge.services.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;
    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }
    private void checkIfProductAlreadyExists(String title, String ownerId) {
        if (this.repository.existsByTitleAndOwnerId(title, ownerId)) {
            throw new ProductAlreadyExistsException();
        }
    }
    @Transactional
    public Product create(CreateProductDTO data) {
        checkIfProductAlreadyExists(data.title(), data.ownerId());
        var category = this.categoryService.getById(data.categoryId());

        var newProduct = new Product("",
                data.title(), data.description(), data.ownerId(), data.price(), category);
        this.repository.save(newProduct);
        return newProduct;
    }
    @Transactional
    public Product update(String id, UpdateProductDTO data) {
        var product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        Category category = null;
        if (data.categoryId() != null && !data.categoryId().isEmpty()) {
            category = this.categoryService.getById(data.categoryId());
        }

        product.modify(data.title(), data.description(), data.price(), category);
        this.repository.save(product);
        return product;
    }
    @Transactional
    public void delete(String id) {
        var product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }
    public Product getById(String id) {
        return this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
    public Page<Product> getAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
