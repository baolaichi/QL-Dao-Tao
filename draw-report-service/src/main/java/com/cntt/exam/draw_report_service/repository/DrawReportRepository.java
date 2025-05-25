package com.cntt.exam.draw_report_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cntt.exam.draw_report_service.model.DrawReport;

@Repository
public interface DrawReportRepository extends JpaRepository<DrawReport, Long> {
    List<DrawReport> findBySemesterAndAcademicYear(int semester, String academicYear);

    List<DrawReport> findBySubjectNameContainingIgnoreCase(String subjectName);
}
