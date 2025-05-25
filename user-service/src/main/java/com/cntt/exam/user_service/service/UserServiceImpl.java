package com.cntt.exam.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntt.exam.user_service.dto.LoginRequest;
import com.cntt.exam.user_service.dto.LoginResponse;
import com.cntt.exam.user_service.dto.RegisterRequest;
import com.cntt.exam.user_service.model.User;
import com.cntt.exam.user_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        return userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .map(user -> new LoginResponse("Login successful", user.getRole()))
                .orElseGet(() -> new LoginResponse("Invalid username or password", null));
    }

    @Override
    public String register(RegisterRequest request) {
    boolean exists = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()).isPresent();
    if (exists) {
        return "Tài khoản đã tồn tại";
    }

    User newUser = new User();
    newUser.setUsername(request.getUsername());
    newUser.setPassword(request.getPassword());
    newUser.setRole(request.getRole().toUpperCase());

    userRepository.save(newUser);
    return "Đăng ký thành công";
}

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
