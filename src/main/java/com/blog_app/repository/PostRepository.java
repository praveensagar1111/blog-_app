package com.blog_app.repository;

import com.blog_app.entity.Category;
import com.blog_app.entity.Post;
import com.blog_app.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post ,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

@Query("select p from Post p where p.title like :key")
//    List<Post> findByTitle(@Param("key")String title);
    List<Post> searchByTitle(@Param("key")String title);

//%keywords% this value is injected to this key
}
