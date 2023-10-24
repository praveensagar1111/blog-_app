package com.blog_app.service;

import com.blog_app.payload.PostDto;
import com.blog_app.payload.PostResponse;

import java.util.List;


public interface PostService {

    PostDto create(PostDto postDto, Integer userId, Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> getPostByCategory(Integer categoryId);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy, String sortDir);

    PostDto getByPostId(Integer postId);


    void deleteById(Integer postId);

    PostDto updateById(Integer postId, PostDto postDto);

    List<PostDto> searchPost(String keyword);
}
