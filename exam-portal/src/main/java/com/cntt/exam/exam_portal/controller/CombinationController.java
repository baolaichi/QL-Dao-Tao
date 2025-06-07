package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.cntt.exam.exam_portal.dto.ExamCombinationDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/combination")
public class CombinationController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showCombinationPage(
            HttpSession session,
            @RequestParam(defaultValue = "3") int semester,
            @RequestParam(defaultValue = "2023-2024") String year,
            Model model) {

        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        List<ExamCombinationDTO> list = apiService.fetchCombinations(semester, year);
        model.addAttribute("combinations", list);
        model.addAttribute("combinationForm", new ExamCombinationDTO());
        return "combination";
    }

    @PostMapping("/save")
    public String saveCombination(@ModelAttribute("combinationForm") ExamCombinationDTO dto,
            HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        apiService.saveCombination(dto);
        return "redirect:/combination";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadCombination(@PathVariable Long id, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        byte[] fileBytes = apiService.downloadCombinationReport(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("tohop_dethi_" + id + ".docx")
                .build());

        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

}
