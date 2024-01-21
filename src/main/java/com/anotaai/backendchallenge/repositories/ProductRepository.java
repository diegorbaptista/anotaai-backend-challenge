package com.anotaai.backendchallenge.repositories;

import com.anotaai.backendchallenge.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
