package com.cntt.exam.exam_structure_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.exam_structure_service.model.ExamStructure;

@Service
public interface ExamStructureService {
    ExamStructure addExamStructure(ExamStructure examStructure);

    List<ExamStructure> getAll();

    List<ExamStructure> getBySemester(int semester, String year);

    ExamStructure updateExamStructure(Long id, ExamStructure examStructure);

    void deleteExamStructure(Long id);
}
