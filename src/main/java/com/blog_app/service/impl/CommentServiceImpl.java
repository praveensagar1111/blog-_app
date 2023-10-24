package com.blog_app.service.impl;

import com.blog_app.entity.Comment;
import com.blog_app.entity.Post;
import com.blog_app.exception.ResourceNotFound;
import com.blog_app.payload.CommentDto;
import com.blog_app.repository.CommentRepository;
import com.blog_app.repository.PostRepository;
import com.blog_app.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment( CommentDto commentDto,Integer postId) {

        Post post= this.postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFound("Post","postId",postId));
        Comment map = this.modelMapper.map(commentDto, Comment.class);

        map.setPost(post);
        Comment save = this.commentRepository.save(map);
        CommentDto map1 = this.modelMapper.map(save, CommentDto.class);

        return map1;
    }

    @Override
    public List<CommentDto> getAll() {

        List<Comment> all = this.commentRepository.findAll();
        List<CommentDto> collect = all.stream().
                map((comment) -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return collect;
    }
}
