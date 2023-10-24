package com.blog_app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CategoryDto {

    private  Integer categoryId;


    @NotBlank(message = "this filed is necessary to filled")
    @NotEmpty(message = "this filed  should be filled")
    private String categoryTitle;



    @NotBlank(message = "this filed is necessary to filled")
    @NotEmpty(message = "this filed  should be filled")
    @Size(min=0,max = 20 ,message = " this filed should be filled with the required filed")
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[ENen]$", message = "Invalid company code format")
    private String categoryDescription;



}
