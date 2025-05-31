package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.exam_portal.dto.DrawReportDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/draw")
public class DrawController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showDrawPage(
            HttpSession session,
            @RequestParam(defaultValue = "3") int semester,
            @RequestParam(defaultValue = "2023-2024") String year,
            Model model) {

        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        List<DrawReportDTO> list = apiService.fetchDrawReports(semester, year);
        model.addAttribute("drawReports", list);
        model.addAttribute("drawForm", new DrawReportDTO());
        return "draw";
    }

    @PostMapping("/save")
    public String saveDraw(@ModelAttribute("drawForm") DrawReportDTO dto,
            HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        apiService.saveDrawReport(dto);
        return "redirect:/draw";
    }
}
