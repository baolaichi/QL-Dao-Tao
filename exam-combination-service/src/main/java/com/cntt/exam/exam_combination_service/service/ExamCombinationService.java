package com.cntt.exam.exam_combination_service.service;

import java.io.IOException;
import java.util.List;

import com.cntt.exam.exam_combination_service.model.ExamCombination;

public interface ExamCombinationService {
    ExamCombination addCombination(ExamCombination e);

    List<ExamCombination> getAll();

    List<ExamCombination> getBySemesterAndYear(int semester, String year);

    ExamCombination updateCombination(Long id, ExamCombination e);

    void delete(Long id);

    byte[] generateReport(ExamCombination examCombination) throws IOException;
}
