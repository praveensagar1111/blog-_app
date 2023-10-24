package com.blog_app.service;

import com.blog_app.payload.CommentDto;

import java.util.List;

public interface CommentService {


    CommentDto createComment( CommentDto commentDto,Integer postId);

    List<CommentDto> getAll();
}
