package com.cntt.exam.user_service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cntt.exam.user_service.dto.LoginRequest;
import com.cntt.exam.user_service.dto.LoginResponse;
import com.cntt.exam.user_service.dto.RegisterRequest;
import com.cntt.exam.user_service.model.User;
import com.cntt.exam.user_service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        //TODO: process POST request
        
        return userService.register(request);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    
    
}
