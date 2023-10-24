package com.blog_app.payload;

import com.blog_app.entity.Category;
import com.blog_app.entity.Comment;
import com.blog_app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {
    private Integer postId;
    private String title;
    private String imageName;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

    private Set<Comment> comments=new HashSet<>();
}
