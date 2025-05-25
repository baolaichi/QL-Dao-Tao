package com.cntt.exam.exam_combination_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cntt.exam.exam_combination_service.model.ExamCombination;

@Repository
public interface ExamCombinationRepository extends JpaRepository<ExamCombination, Long> {
    List<ExamCombination> findBySemesterAndAcademicYear(int semester, String academicYear);
}
