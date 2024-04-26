package com.example.casestudylibrary.controller.rest;

import com.example.casestudylibrary.domain.dto.req.CategoryReqDto;
import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import com.example.casestudylibrary.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryRestAPI {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResDto>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResDto> findCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CategoryReqDto categoryReqDto) {
        categoryService.save(categoryReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryReqDto categoryReqDto) {
        categoryService.updateById(id, categoryReqDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
