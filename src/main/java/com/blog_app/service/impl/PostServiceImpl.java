package com.blog_app.service.impl;

import com.blog_app.entity.Category;
import com.blog_app.entity.Post;
import com.blog_app.entity.User;
import com.blog_app.exception.ResourceNotFound;
import com.blog_app.payload.PostDto;
import com.blog_app.payload.PostResponse;
import com.blog_app.repository.CategoryRepository;
import com.blog_app.repository.PostRepository;
import com.blog_app.repository.UserRepository;
import com.blog_app.service.PostService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDto create(PostDto postDto, Integer userId,Integer categoryId) {

        User user=this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFound("User","userId",userId));
        Category category=this.categoryRepository.
                findById(categoryId).
                orElseThrow(()-> new ResourceNotFound("Category","categoryId",categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post save =this.postRepository.save(post);
        PostDto map = this.modelMapper.map(save, PostDto.class);

        return map;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user=this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFound("User","userId",userId));
        List<Post> byUser = this.postRepository.findByUser(user);
        List<PostDto> collect = byUser.stream().
                map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).
                orElseThrow(()->  new ResourceNotFound("Category","categoryId",categoryId));
        List<Post> byCategory = this.postRepository.findByCategory(category);

        List<PostDto> collect = byCategory.stream().map((posts) -> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy, String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

// create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);

        Page<Post> posts = postRepository.findAll(pageable);


        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().
                map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());//here the pageNumber get from the posts i.e 92 line
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElement((int) posts.getTotalElements());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;//return the 101

//        List<Post> all = this.postRepository.findAll();
//    List<PostDto> collects = all.stream().
//            map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//        return collects;
}


    @Override
    public PostDto getByPostId(Integer postId) {
        Post post = this.postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFound("Post", "PostId", postId));
        PostDto map = this.modelMapper.map(post, PostDto.class);
        return map;
    }

    @Override
    public void deleteById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow
                (() -> new ResourceNotFound("Post", "PostId", postId));
        this.postRepository.delete(post);
//this.postRepository.deleteById(postId);
    }

    @Override
    public PostDto updateById(Integer postId, PostDto postDto) {

        Post post = this.postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        Post save = this.postRepository.save(post);


        return this.modelMapper.map(save, PostDto.class);
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
        List<PostDto> collect = posts.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

}
