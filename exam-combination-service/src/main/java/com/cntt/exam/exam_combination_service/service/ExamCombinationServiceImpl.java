package com.cntt.exam.exam_combination_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.exam_combination_service.model.ExamCombination;
import com.cntt.exam.exam_combination_service.repository.ExamCombinationRepository;

@Service
public class ExamCombinationServiceImpl implements ExamCombinationService {
    private final ExamCombinationRepository eRepository;

    public ExamCombinationServiceImpl(ExamCombinationRepository eRepository) {
        this.eRepository = eRepository;
    }

    @Override
    public ExamCombination addCombination(ExamCombination e) {
        return eRepository.save(e);
    }

    @Override
    public List<ExamCombination> getAll() {
        return eRepository.findAll();
    }

    @Override
    public List<ExamCombination> getBySemesterAndYear(int semester, String year) {
        return eRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public ExamCombination updateCombination(Long id, ExamCombination e) {
        ExamCombination exam = eRepository.findById(id).orElseThrow();
        exam.setSubjectName(e.getSubjectName());
        exam.setSubjectCode(e.getSubjectCode());
        exam.setExamType(e.getExamType());
        exam.setSemester(e.getSemester());
        exam.setAcademicYear(e.getAcademicYear());
        exam.setQuestionCodes(e.getQuestionCodes());
        exam.setNote(e.getNote());

        return eRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        eRepository.deleteById(id);
    }
}
