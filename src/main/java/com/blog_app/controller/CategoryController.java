package com.blog_app.controller;

import com.blog_app.payload.ApiResponse;
import com.blog_app.payload.CategoryDto;
import com.blog_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    //http://localhost:8080/api/category/
    @PostMapping("/")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto ){
        CategoryDto categoryDto1=this.categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/category/1
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.update(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);


    }
    //http://localhost:8080/api/category/1
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer categoryId){
        this.categoryService.delete(categoryId);
return new ResponseEntity<>(new ApiResponse("category is deleted",true),HttpStatus.OK);
    }
    //getAll
    //http://localhost:8080/api/category/all
    @GetMapping("/all")
public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDtos=this.categoryService.getAll();
        return ResponseEntity.ok(categoryDtos);
    }
    //getById
    //http://localhost:8080/api/category/1
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }


}
