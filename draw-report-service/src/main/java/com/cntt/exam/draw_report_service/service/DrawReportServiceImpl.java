package com.cntt.exam.draw_report_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.draw_report_service.model.DrawReport;
import com.cntt.exam.draw_report_service.repository.DrawReportRepository;

@Service
public class DrawReportServiceImpl implements DrawReportService {
    private final DrawReportRepository drawReportRepository;

    public DrawReportServiceImpl(DrawReportRepository drawReportRepository) {
        this.drawReportRepository = drawReportRepository;
    }

    @Override
    public DrawReport addReport(DrawReport drawReport) {
        return drawReportRepository.save(drawReport);
    }

    @Override
    public List<DrawReport> getAll() {
        return drawReportRepository.findAll();
    }

    @Override
    public List<DrawReport> getBySemester(int semester, String year) {
        return drawReportRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public DrawReport updateReport(Long id, DrawReport drawReport) {
        DrawReport entityReport = drawReportRepository.findById(id).orElseThrow();
        entityReport.setSubjectName(drawReport.getSubjectName());
        entityReport.setSubjectCode(drawReport.getSubjectCode());
        entityReport.setSemester(drawReport.getSemester());
        entityReport.setAcademicYear(drawReport.getAcademicYear());
        entityReport.setExamDate(drawReport.getExamDate());
        entityReport.setExamShift(drawReport.getExamShift());
        entityReport.setNumberOfQuestions(drawReport.getNumberOfQuestions());
        entityReport.setNote(drawReport.getNote());

        return drawReportRepository.save(entityReport);
    }

    @Override
    public void deleteReport(Long id) {
        drawReportRepository.deleteById(id);
    }
}
