package com.blog_app.controller;

import com.blog_app.payload.CommentDto;
import com.blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;


//http://localhost:8080/api/post/1/comment
    //create
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> create ( @RequestBody CommentDto commentDto,@PathVariable Integer postId){
        CommentDto comment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    ///get
//http://localhost:8080/api/comment/all

    @GetMapping("/comment/all")
    public ResponseEntity<List<CommentDto>> getAll(){
        List<CommentDto> commentDtos=this.commentService.getAll();
        return ResponseEntity.ok(commentDtos);
    }



}
