package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cntt.exam.exam_portal.dto.ExamCombinationDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import java.util.List;

@Controller
@RequestMapping("/combination")
public class CombinationController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showCombinationPage(Model model) {
        List<ExamCombinationDTO> list = apiService.fetchCombinations(3, "2023-2024");
        model.addAttribute("combinations", list);
        model.addAttribute("combinationForm", new ExamCombinationDTO());
        return "combination";
    }

    @PostMapping("/save")
    public String saveCombination(@ModelAttribute("combinationForm") ExamCombinationDTO dto) {
        apiService.saveCombination(dto);
        return "redirect:/combination";
    }
}
