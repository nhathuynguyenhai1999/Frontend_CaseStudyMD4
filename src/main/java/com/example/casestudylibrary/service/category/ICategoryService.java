package com.example.casestudylibrary.service.category;

import com.example.casestudylibrary.service.category.request.CategorySaveRequest;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ICategoryService {

    void create (CategorySaveRequest request);
    void update (CategorySaveRequest request, Long id) throws JsonMappingException;

    void delete (Long id);
}
