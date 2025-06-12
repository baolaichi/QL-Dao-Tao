package com.cntt.exam.exam_portal.controller;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

    @GetMapping("/report/pdf")
    public ResponseEntity<ByteArrayResource> exportPdf(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role) && !"THANHTRA".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/structures/report/pdf?semester=3&year=2023-2024";

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_PDF));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    byte[].class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ByteArrayResource resource = new ByteArrayResource(response.getBody());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_hk3.pdf")
                        .contentLength(response.getBody().length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @GetMapping("/report/")
    public String export(Model model) {
        return "report";
    }

    @GetMapping("/report/pdf/all")
    public ResponseEntity<ByteArrayResource> exportAllPdf(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role) && !"THANHTRA".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/structures/report/pdf/all"; // <-- gọi API xuất toàn bộ

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_PDF));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    byte[].class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ByteArrayResource resource = new ByteArrayResource(response.getBody());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_tatca.pdf")
                        .contentLength(response.getBody().length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @GetMapping("/report/word/all")
    public ResponseEntity<ByteArrayResource> exportAllWord(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role) && !"THANHTRA".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/structures/report/word/all"; // Gọi backend Word

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(
                    MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document")));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    byte[].class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ByteArrayResource resource = new ByteArrayResource(response.getBody());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_cautruc.docx")
                        .contentLength(response.getBody().length)
                        .contentType(MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

}
