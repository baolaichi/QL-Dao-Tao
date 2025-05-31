package com.cntt.exam.exam_portal.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

    @GetMapping("/report/pdf")
    public ResponseEntity<ByteArrayResource> exportPdf(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"THANHTRA".equals(role) && !"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/reports/pdf?semester=3&year=2023-2024";

        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        ByteArrayResource resource = new ByteArrayResource(response.getBody());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_hk3.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
