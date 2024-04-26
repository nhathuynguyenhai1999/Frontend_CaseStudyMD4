package com.example.casestudylibrary.domain.dto.res;

import com.example.casestudylibrary.domain.Category;
import com.example.casestudylibrary.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {

    private Long id;
    private String name;
    private String description;
    private Image image;
    private String publisher;
    private String status;
    private String categoryName;
    private CategoryResDto category;

    public BookResDto (Long id, String name, String description,Image image, String publisher, String status, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.publisher = publisher;
        this.status = status;
        this.categoryName = category.getName();
        this.category = category.toCategoryResDto();
    }

    public BookResDto (Long id, String name, String description, String publisher, String status, CategoryResDto category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.status = status;
        this.category = category;
    }

    public BookResDto (Long id, String name, String description, String publisher, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.categoryName = category.getName();
    }


}
