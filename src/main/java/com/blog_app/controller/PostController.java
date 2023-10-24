package com.blog_app.controller;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.blog_app.config.AppConstants;
import com.blog_app.payload.ApiResponse;
import com.blog_app.payload.PostDto;

import com.blog_app.payload.PostResponse;
import com.blog_app.service.FileService;
import com.blog_app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;



    @Autowired
    private FileService fileService;


    @Value("${project.image}")

    private String path;
     //create
    //http://localhost/8080/api/user/1/category/1/post
    @PostMapping("/user/{userId}/category/{categoryId}/post")

    public ResponseEntity<?> createPost(@RequestBody PostDto postDto,
                                        @PathVariable Integer userId,
                                        @PathVariable Integer categoryId){
        PostDto postDto1=this.postService.create(postDto,userId,categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    //get By user

    @GetMapping("/user/{userId}/post")

    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList=this.postService.getPostByUser(userId);
        return  ResponseEntity.ok(postDtoList);
    }
    // get the post by the CategoryId

@GetMapping("/category/{categoryId}/post")
public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> list=this.postService.getPostByCategory(categoryId);
        return  ResponseEntity.ok(list);
}
// get all post
@GetMapping("/all")
public ResponseEntity<PostResponse> getAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
                                           @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String
                                                   sortDir
                          ){
    PostResponse allPost = this.postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
return ResponseEntity.ok(allPost);
}

    //get post By Id

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable Integer postId){
        PostDto postDto=this.postService.getByPostId(postId);
    return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
//delete By Id

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deleteByPostId(@PathVariable Integer postId){
        this.postService.deleteById(postId);

    return  new ResponseEntity<ApiResponse>(new ApiResponse("deleted postId",true),HttpStatus.OK);
    }

    ////========================================================================
//// other way to create the Response Entity
//@DeleteMapping("/post/{postId}")
//    public ApiResponse deletePost(@PathVariable Integer postId){
//        this.postService.deletePost(postId);
//        return  new ApiResponse("Post deleted",true);
//}
//
//    ==================================================================

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updateBYId (@PathVariable Integer postId,@RequestBody PostDto postDto){
    PostDto postDto1=this.postService.updateById(postId,postDto);
    return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }


    //search
    @GetMapping("/posts/search/{keywords}")
    public  ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
        List<PostDto> list = this.postService.searchPost(keywords);
return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);

    }
// post image upload

    @PostMapping("/post/image/upload/{postId}")

    public  ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
    @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getByPostId(postId);


        String fileName = this.fileService.uploadImage(path, image);

        postDto.setImageName(fileName);
        PostDto postDto1 = this.postService.updateById(postId, postDto);
return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
//serve file method
    @GetMapping(value = "post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response
    ) throws IOException{
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
