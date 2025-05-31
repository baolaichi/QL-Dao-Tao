package com.cntt.exam.user_service.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.user_service.dto.LoginRequest;
import com.cntt.exam.user_service.dto.LoginResponse;
import com.cntt.exam.user_service.dto.RegisterRequest;
import com.cntt.exam.user_service.model.User;
import com.cntt.exam.user_service.repository.UserRepository;
import com.cntt.exam.user_service.service.UserService;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        // Kiểm tra username đã tồn tại
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Tài khoản đã tồn tại");
        }

        userService.register(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tài khoản");
        }
        userRepository.deleteByUsername(username);
        return ResponseEntity.ok("Đã xoá");
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportUsersToExcel() throws IOException {
        List<User> users = userRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Users");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Username");
        header.createCell(1).setCellValue("Role");

        int rowIndex = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(user.getUsername());
            row.createCell(1).setCellValue(user.getRole());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        byte[] excelData = out.toByteArray();
        out.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("users.xlsx").build());

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }

}
