package com.example.casestudylibrary.service.impl;

import com.example.casestudylibrary.domain.Category;
import com.example.casestudylibrary.domain.dto.req.CategoryReqDto;
import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import com.example.casestudylibrary.repository.ICategoryRepository;

import com.example.casestudylibrary.service.ICategoryService;
import com.example.casestudylibrary.service.book.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final IBookService bookService;
    @Override
    public List<CategoryResDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResDto> categoryResDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryResDtos.add(category.toCategoryResDto());
        }
        return categoryResDtos;
    }

    @Override
    public CategoryResDto save(CategoryReqDto categoryReqDto) {
       Category category = new Category();
       category.setName(categoryReqDto.getName());
       categoryRepository.save(category);

        return category.toCategoryResDto();

    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
        // delete all books in this category
    }

    @Override
    public void updateById(Long id, CategoryReqDto categoryReqDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        category.setName(categoryReqDto.getName());
        categoryRepository.save(category);


    }

    @Override
    public CategoryResDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            return category.toCategoryResDto();
        }
        return null;
    }
}
