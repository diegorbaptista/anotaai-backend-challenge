package com.anotaai.backendchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"title", "ownerId"})
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public void modify(String title, String description, Integer price, Category category) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
        if (description != null && !description.isBlank()) {
            this.price = price;
        }
        if (price > 0) {
            this.price = price;
        }
        if (category != null) {
            this.category = category;
        }
    }

}
