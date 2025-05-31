package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cntt.exam.exam_portal.dto.UserDTO;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/api/users";

    @GetMapping
    public String userList(@RequestParam(value = "keyword", required = false) String keyword,
            HttpSession session,
            Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/dashboard";
        }

        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {
                });

        List<UserDTO> users = response.getBody();

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = users.stream()
                    .filter(u -> u.getUsername().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("users", users);
        return "admin-users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserDTO userDTO,
            HttpSession session,
            RedirectAttributes redirect) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/dashboard";
        }

        try {
            restTemplate.postForEntity(BASE_URL + "/register", userDTO, Void.class);
            redirect.addFlashAttribute("success", "Đã thêm người dùng thành công.");
        } catch (HttpClientErrorException.Conflict e) {
            redirect.addFlashAttribute("error", "Tài khoản đã tồn tại.");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Lỗi khi thêm người dùng.");
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("username") String username,
            HttpSession session,
            RedirectAttributes redirect) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/dashboard";
        }

        try {
            restTemplate.delete(BASE_URL + "/delete/" + username);
            redirect.addFlashAttribute("success", "Đã xoá người dùng: " + username);
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Không thể xoá người dùng.");
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportUsersExcel(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String url = "http://localhost:8080/api/users/export/excel";
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        ByteArrayResource resource = new ByteArrayResource(response.getBody());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx")
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

}
