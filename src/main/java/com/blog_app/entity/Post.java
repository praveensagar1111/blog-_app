package com.blog_app.entity;

import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

@Column(name="title",length = 10000)
    private String title;

    private String imageName;
    private Date addedDate;



    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments=new HashSet<>();
}
