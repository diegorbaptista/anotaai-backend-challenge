package com.anotaai.backendchallenge.repositories;

import com.anotaai.backendchallenge.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsCategoryByTitleAndOwnerId(String title, String ownerId);
    List<Category> findAllByOwnerId(String ownerId);
}
