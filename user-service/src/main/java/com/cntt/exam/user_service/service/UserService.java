package com.cntt.exam.user_service.service;


import java.util.List;

import com.cntt.exam.user_service.dto.LoginRequest;
import com.cntt.exam.user_service.dto.LoginResponse;
import com.cntt.exam.user_service.dto.RegisterRequest;
import com.cntt.exam.user_service.model.User;

public interface UserService {
    LoginResponse login(LoginRequest request);
    String register(RegisterRequest request);
    List<User> getAllUsers();
}
