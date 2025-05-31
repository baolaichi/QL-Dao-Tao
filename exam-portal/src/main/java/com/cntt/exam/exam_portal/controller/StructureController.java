package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.exam_portal.dto.ExamStructureDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/structure")
public class StructureController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showStructurePage(HttpSession session, Model model,
            @RequestParam(defaultValue = "3") int semester,
            @RequestParam(defaultValue = "2023-2024") String year) {

        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        List<ExamStructureDTO> list = apiService.fetchStructures(semester, year);
        model.addAttribute("structures", list);
        model.addAttribute("structureForm", new ExamStructureDTO());
        return "structure";
    }

    @PostMapping("/save")
    public String saveStructure(@ModelAttribute("structureForm") ExamStructureDTO dto) {
        apiService.saveStructure(dto);
        return "redirect:/structure";
    }
}
