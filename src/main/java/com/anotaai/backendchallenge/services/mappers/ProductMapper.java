package com.anotaai.backendchallenge.services.mappers;

import com.anotaai.backendchallenge.domain.Product;
import com.anotaai.backendchallenge.services.dto.ProductDTO;

import java.util.function.Function;

public class ProductMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(),
                product.getOwnerId(), product.getCategory().getId());
    }
}
