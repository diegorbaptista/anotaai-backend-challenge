package com.anotaai.backendchallenge.repositories;

import com.anotaai.backendchallenge.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByTitleAndOwnerId(String title, String ownerId);
    List<Product> findAllByOwnerId(String ownerId);
}
