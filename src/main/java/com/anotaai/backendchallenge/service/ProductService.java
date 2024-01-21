package com.anotaai.backendchallenge.service;

import com.anotaai.backendchallenge.domain.Category;
import com.anotaai.backendchallenge.domain.Product;
import com.anotaai.backendchallenge.repositories.ProductRepository;
import com.anotaai.backendchallenge.service.dto.CreateProductDTO;
import com.anotaai.backendchallenge.service.dto.UpdateProductDTO;
import com.anotaai.backendchallenge.service.exceptions.ProductAlreadyExistsException;
import com.anotaai.backendchallenge.service.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

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
    public Product create(CreateProductDTO data) {
        checkIfProductAlreadyExists(data.title(), data.ownerId());
        var category = this.categoryService.getById(data.categoryId());

        var newProduct = new Product("",
                data.title(), data.description(), data.ownerId(), data.price(), category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product update(UpdateProductDTO data) {
        var product = this.repository.findById(data.id()).orElseThrow(ProductNotFoundException::new);
        checkIfProductAlreadyExists(data.title(), product.getOwnerId());

        Category category = null;
        if (data.categoryId() != null && !data.categoryId().isEmpty()) {
            category = this.categoryService.getById(data.categoryId());
        }

        product.modify(data.title(), data.description(), data.price(), category);
        this.repository.save(product);
        return product;
    }

    public void delete(String id) {
        var product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }

    public Product getById(String id) {
        return this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> getAllByOwnerId(String ownerId) {
        return this.repository.findAllByOwnerId(ownerId);
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

}
