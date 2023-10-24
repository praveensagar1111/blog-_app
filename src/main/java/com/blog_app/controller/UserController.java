package com.blog_app.controller;

import com.blog_app.payload.ApiResponse;
import com.blog_app.payload.CreateResponse;
import com.blog_app.payload.UserDto;
import com.blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")

public class UserController {
@Autowired
    private UserService userService;
 // create the user

//http://localhost:8080/api/users
    @PostMapping("/")

    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto){
        UserDto dto = this.userService.create(userDto);
        return  new ResponseEntity<>(new CreateResponse("created",true), HttpStatus.CREATED);
    }

    //get the all List


    //http://localhost:8080/api/users/all
@GetMapping("/all")
public List<UserDto> getAll(){
        List<UserDto> list=this.userService.getAll();
        return list;
}
//http://localhost:8080/api/users/1

//update
    @PutMapping("/{id}")

    public ResponseEntity<?> updateUser(@Valid  @RequestBody UserDto userDto, @PathVariable("id") Integer id){
        UserDto userDto1=this.userService.updateUser(userDto,id);
        return  new ResponseEntity<>(userDto1,HttpStatus.OK);



    }

    // delete the user

    //http://localhost:8080/api/users/1
    @DeleteMapping("/{id}")

    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return  new ResponseEntity<>(new ApiResponse("User deleted successful",true),HttpStatus.OK);



    }
    //http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public  ResponseEntity<?> GetById(@PathVariable Integer id){
        UserDto userDto=this.userService.getById(id);
    return  new ResponseEntity<>(userDto,HttpStatus.OK);
    }


}
