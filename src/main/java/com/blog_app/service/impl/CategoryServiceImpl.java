package com.blog_app.service.impl;

import com.blog_app.entity.Category;
import com.blog_app.exception.ResourceNotFound;
import com.blog_app.payload.CategoryDto;
import com.blog_app.repository.CategoryRepository;
import com.blog_app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category map = this.modelMapper.map(categoryDto, Category.class);
        Category save = this.categoryRepository.save(map);
        CategoryDto map1 = this.modelMapper.map(save, CategoryDto.class);
        return map1;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFound("Category", "categoryId", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = this.categoryRepository.save(category);
        CategoryDto map = this.modelMapper.map(save, CategoryDto.class);
        return map;
    }

    @Override
    public void delete(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFound("Category", "categoryId", categoryId));
        this.categoryRepository.deleteById(categoryId);

    }

    @Override
    public CategoryDto getById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFound("Category", "categoryId", categoryId));
        CategoryDto map = this.modelMapper.map(category, CategoryDto.class);
        return map;
    }

    @Override
    public List<CategoryDto> getAll() {

        List<Category> all = this.categoryRepository.findAll();
        List<CategoryDto> collect = all.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }
}
