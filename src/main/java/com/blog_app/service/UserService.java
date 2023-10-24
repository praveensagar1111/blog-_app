package com.blog_app.service;

import com.blog_app.payload.UserDto;

import java.util.List;

public interface UserService {


    UserDto create(UserDto userDto);



    List<UserDto> getAll();

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUser(Integer id);

    UserDto getById(Integer id);
}
