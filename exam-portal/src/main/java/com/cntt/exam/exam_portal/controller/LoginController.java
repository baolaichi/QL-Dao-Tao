package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cntt.exam.exam_portal.dto.LoginRequest;
import com.cntt.exam.exam_portal.dto.LoginResponse;
import com.cntt.exam.exam_portal.dto.RegisterRequest;
import com.cntt.exam.exam_portal.dto.UserDTO;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        String url = "http://localhost:8080/api/users/login";
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(url, loginRequest, LoginResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getRole() != null) {
            session.setAttribute("username", loginRequest.getUsername());
            session.setAttribute("role", response.getBody().getRole());
            return "redirect:/dashboard";
        }

        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest,
            Model model,
            HttpSession session) {
        // Kiểm tra confirm mật khẩu
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            model.addAttribute("error", "Mật khẩu không khớp.");
            return "register";
        }

        // Gửi tới user-service
        String url = "http://localhost:8080/api/users/register";
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(registerRequest.getUsername());
        userDTO.setPassword(registerRequest.getPassword());
        userDTO.setRole(registerRequest.getRole());

        try {
            // gọi REST để đăng ký
            ResponseEntity<String> response = restTemplate.postForEntity(url, userDTO, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // ✅ Tự động đăng nhập sau đăng ký
                session.setAttribute("username", userDTO.getUsername());
                session.setAttribute("role", userDTO.getRole());
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Đăng ký thất bại: " + response.getBody());
                return "register";
            }

        } catch (HttpClientErrorException e) {
            // Trường hợp trùng username
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                model.addAttribute("error", "Tài khoản đã tồn tại!");
            } else {
                model.addAttribute("error", "Lỗi: " + e.getStatusCode());
            }
            return "register";
        }
    }

}
