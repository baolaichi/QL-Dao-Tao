package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.client.RestTemplate;

import com.cntt.exam.exam_portal.dto.LoginRequest;
import com.cntt.exam.exam_portal.dto.LoginResponse;

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
}
