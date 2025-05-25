package com.cntt.exam.exam_combination_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cntt.exam.exam_combination_service.model.ExamCombination;
import com.cntt.exam.exam_combination_service.service.ExamCombinationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/combinations")
@CrossOrigin
public class ExamCombinationController {
    private ExamCombinationService eService;

    public ExamCombinationController(ExamCombinationService eService) {
        this.eService = eService;
    }

    @PostMapping
    public ExamCombination createCombination(@RequestBody ExamCombination e) {
        // TODO: process POST request

        return eService.addCombination(e);
    }

    @GetMapping
    public List<ExamCombination> getAll() {
        return eService.getAll();
    }

    @GetMapping("/semester/{semester}")
    public List<ExamCombination> getBySemester(@PathVariable int semester,
            @RequestParam String year) {
        return eService.getBySemesterAndYear(semester, year);
    }

    @PutMapping("/{id}")
    public ExamCombination putMethodName(@PathVariable Long id, @RequestBody ExamCombination e) {
        // TODO: process PUT request

        return eService.updateCombination(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eService.delete(id);
    }

}
