package com.blog_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Integer id;
    @Column(name="user_name",nullable = false,length =100)

    private String name;
    private String email;
    private String password;
    private String about;
}
