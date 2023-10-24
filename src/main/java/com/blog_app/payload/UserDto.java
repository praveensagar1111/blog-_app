package com.blog_app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private int id;
    @NotBlank///mandatory
    @NotEmpty
    @Size(min = 4,message = "user name must be of 4 character")
    private String name;

    @Email(message = "email invalid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10,message = "password should be 3 character")
    private String password;

    @Size(min = 5,max = 100,message = "the about should be between  5 and 100 character")
    @NotEmpty
    private String about;
}
