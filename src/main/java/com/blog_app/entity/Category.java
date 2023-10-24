package com.blog_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor

public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Integer categoryId;

 private String categoryTitle;
 private String categoryDescription;



 // one category have the multiple post for the  one category
 @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<Post> posts=new ArrayList<>();
}
