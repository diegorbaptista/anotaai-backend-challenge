package com.anotaai.backendchallenge.controllers;

import com.anotaai.backendchallenge.services.ProductService;
import com.anotaai.backendchallenge.services.dtos.products.CreateProductDTO;
import com.anotaai.backendchallenge.services.dtos.products.ProductDTO;
import com.anotaai.backendchallenge.services.dtos.products.UpdateProductDTO;
import com.anotaai.backendchallenge.services.mappers.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;
    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid CreateProductDTO data, UriComponentsBuilder builder) {
        var product = this.service.create(data);
        var uri = builder.path("/api/products/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.apply(product));
    }
    @PutMapping("{productId}")
    public ResponseEntity<ProductDTO> update(@PathVariable("productId") String productId,
                                             @RequestBody @Valid UpdateProductDTO data) {
        var product = this.service.update(productId, data);
        return ResponseEntity.ok(mapper.apply(product));
    }
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") String productId) {
        this.service.delete(productId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("productId") String productId) {
        var product = this.service.getById(productId);
        return ResponseEntity.ok(mapper.apply(product));
    }
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> list(@PageableDefault Pageable pageable) {
        var products = this.service.getAll(pageable);
        return ResponseEntity.ok(products.map(mapper));
    }
}
