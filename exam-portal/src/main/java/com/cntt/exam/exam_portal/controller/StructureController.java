package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.exam_portal.dto.ExamStructureDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import java.util.List;

@Controller
@RequestMapping("/structure")
public class StructureController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showStructurePage(Model model) {
        List<ExamStructureDTO> list = apiService.fetchStructures(3, "2023-2024");
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
