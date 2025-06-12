package com.cntt.exam.draw_report_service.service;

import java.io.IOException;
import java.util.List;

import com.cntt.exam.draw_report_service.model.DrawReport;

public interface DrawReportService {
    DrawReport addReport(DrawReport drawReport);

    List<DrawReport> getAll();

    List<DrawReport> getBySemester(int semester, String year);

    DrawReport updateReport(Long id, DrawReport drawReport);

    void deleteReport(Long id);

    byte[] exportDrawReport(List<DrawReport> reports, int semester, String academicYear) throws IOException;

    public byte[] exportReport(int semester, String year) throws IOException;

    DrawReport getById(Long id);
}
