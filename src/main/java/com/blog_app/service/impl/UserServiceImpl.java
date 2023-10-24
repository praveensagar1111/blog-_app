package com.blog_app.service.impl;

import com.blog_app.entity.User;
import com.blog_app.exception.ResourceNotFound;
import com.blog_app.payload.UserDto;
import com.blog_app.repository.UserRepository;
import com.blog_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto create(UserDto userDto) {

//        User user = entityToDto(userDto);
//        User save = this.userRepository.save(user);
//        UserDto dto = dtoToEntity(save);
//        return dto;


        User userEntity = this.modelMapper.map(userDto, User.class);
        User save = this.userRepository.save(userEntity);
        UserDto map = this.modelMapper.map(save, UserDto.class);
     return map;
    }


    @Override
    public List<UserDto> getAll() {
        List<User> all = this.userRepository.findAll();
        List<UserDto> collect = all.stream().map(this::dtoToEntity).collect(Collectors.toList());
        return collect;

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User userNotFound = this.userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User"));

userNotFound.setName(userDto.getName());
userNotFound.setEmail(userDto.getEmail());
userNotFound.setPassword(userDto.getPassword());
userNotFound.setAbout(userDto.getAbout());
        User save = this.userRepository.save(userNotFound);
        UserDto dto = dtoToEntity(save);
        return dto;

    }

    @Override
    public void deleteUser(Integer id) {
        User userNotFound = this.userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFound("User","id",  id));
this.userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Integer id) {
        User user = this.userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"+id));//runti,e exception
        UserDto dto = dtoToEntity(user);
        return  dto;
    }

    User entityToDto(UserDto userDto){
        User entity = modelMapper.map(userDto, User.class);
        return  entity;
    }
    UserDto dtoToEntity(User user){
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }
}
