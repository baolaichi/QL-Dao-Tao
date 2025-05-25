package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.exam_portal.dto.DrawReportDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import java.util.List;

@Controller
@RequestMapping("/draw")
public class DrawController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showDrawPage(Model model) {
        List<DrawReportDTO> list = apiService.fetchDrawReports(3, "2023-2024");
        model.addAttribute("drawReports", list);
        model.addAttribute("drawForm", new DrawReportDTO());
        return "draw";
    }

    @PostMapping("/save")
    public String saveDraw(@ModelAttribute("drawForm") DrawReportDTO dto) {
        apiService.saveDrawReport(dto);
        return "redirect:/draw";
    }
}
