package com.cntt.exam.draw_report_service.service;

import java.util.List;

import com.cntt.exam.draw_report_service.model.DrawReport;

public interface DrawReportService {
    DrawReport addReport(DrawReport drawReport);

    List<DrawReport> getAll();

    List<DrawReport> getBySemester(int semester, String year);

    DrawReport updateReport(Long id, DrawReport drawReport);

    void deleteReport(Long id);
}
