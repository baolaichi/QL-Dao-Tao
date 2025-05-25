package com.cntt.exam.exam_structure_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cntt.exam.exam_structure_service.model.ExamStructure;
import com.cntt.exam.exam_structure_service.service.ExamStructureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/structures")
@CrossOrigin
public class ExamStructureController {

    private final ExamStructureService eService;

    public ExamStructureController(ExamStructureService eService) {
        this.eService = eService;
    }

    @PostMapping
    public ExamStructure createStructure(@RequestBody ExamStructure entity) {
        return eService.addExamStructure(entity);
    }

    @GetMapping
    public List<ExamStructure> getAll() {
        return eService.getAll();
    }

    @GetMapping("/semester/{semester}")
    public List<ExamStructure> getBySemester(@RequestParam String year,
            @PathVariable int semester) {
        return eService.getBySemester(semester, year);
    }

    @PutMapping("/{id}")
    public ExamStructure updateExamStructure(@PathVariable Long id, @RequestBody ExamStructure e) {
        // TODO: process PUT request

        return eService.updateExamStructure(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eService.deleteExamStructure(id);
    }

}
