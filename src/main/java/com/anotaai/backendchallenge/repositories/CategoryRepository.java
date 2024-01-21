package com.anotaai.backendchallenge.repositories;

import com.anotaai.backendchallenge.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
