package com.example.casestudylibrary.service.impl;

import com.example.casestudylibrary.domain.Category;
import com.example.casestudylibrary.domain.dto.req.CategoryReqDto;
import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import com.example.casestudylibrary.repository.ICategoryRepository;
import com.example.casestudylibrary.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<CategoryResDto> findAll() {
        /*
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories){
            CategoryResDto categoryResDto = new CategoryResDto();
            categoryResDto.setId(category.getId());
            categoryResDto.setName(category.getName());
        }
         */
        return categoryRepository.findAll().
                stream().map(c -> new CategoryResDto(c.getId(), c.getName()))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, CategoryReqDto categoryReqDto) {

    }

    @Override
    public CategoryResDto findById(Long id) {
        return null;
    }

    @Override
    public CategoryResDto save(CategoryReqDto categoryReqDto) {
        Category category = new Category();
        category.setName(categoryReqDto.getName());
        categoryRepository.save(category);

        return category.toCategoryResDto();
    }


}
