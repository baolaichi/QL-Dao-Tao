package com.cntt.exam.exam_structure_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.exam_structure_service.model.ExamStructure;
import com.cntt.exam.exam_structure_service.repository.ExamStructureRepository;

@Service
public class ExamStructureServiceImpl implements ExamStructureService {
    private final ExamStructureRepository eRepository;

    public ExamStructureServiceImpl(ExamStructureRepository eRepository) {
        this.eRepository = eRepository;
    }

    @Override
    public ExamStructure addExamStructure(ExamStructure examStructure) {
        return eRepository.save(examStructure);
    }

    @Override
    public List<ExamStructure> getAll() {
        return eRepository.findAll();
    }

    @Override
    public List<ExamStructure> getBySemester(int semester, String year) {
        return eRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public ExamStructure updateExamStructure(Long id, ExamStructure newStructure) {
        ExamStructure e = eRepository.findById(id).orElseThrow();
        e.setSubjectName(newStructure.getSubjectName());
        e.setSubjectCode(newStructure.getSubjectCode());
        e.setExamType(newStructure.getExamType());
        e.setSemester(newStructure.getSemester());
        e.setAcademicYear(newStructure.getAcademicYear());
        e.setDifficultyLevel(newStructure.getDifficultyLevel());
        e.setStructureDescription(newStructure.getStructureDescription());
        return eRepository.save(e);
    }

    @Override
    public void deleteExamStructure(Long id) {
        eRepository.deleteById(id);
    }
}
