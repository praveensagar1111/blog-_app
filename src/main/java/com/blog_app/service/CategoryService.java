package com.blog_app.service;

import com.blog_app.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto, Integer categoryId);

    void delete(Integer categoryId);

    CategoryDto getById(Integer categoryId);

    List<CategoryDto> getAll();
}
