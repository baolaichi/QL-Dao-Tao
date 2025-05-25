package com.cntt.exam.exam_structure_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cntt.exam.exam_structure_service.model.ExamStructure;

@Repository
public interface ExamStructureRepository extends JpaRepository<ExamStructure, Long> {
    List<ExamStructure> findBySemesterAndAcademicYear(int semester, String academicYear);

    List<ExamStructure> findBySubjectNameContainingIgnoreCase(String subjectName);

}
