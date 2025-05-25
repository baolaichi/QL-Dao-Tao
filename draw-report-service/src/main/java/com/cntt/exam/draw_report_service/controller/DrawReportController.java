package com.cntt.exam.draw_report_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cntt.exam.draw_report_service.model.DrawReport;
import com.cntt.exam.draw_report_service.service.DrawReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/draw-reports")
public class DrawReportController {
    private final DrawReportService drawReportService;

    public DrawReportController(DrawReportService drawReportService) {
        this.drawReportService = drawReportService;
    }

    @PostMapping
    public DrawReport create(@RequestBody DrawReport drawReport) {
        // TODO: process POST request

        return drawReportService.addReport(drawReport);
    }

    @GetMapping
    public List<DrawReport> getAllReports() {
        return drawReportService.getAll();
    }

    @GetMapping("/semester/{semester}")
    public List<DrawReport> getBySemester(@PathVariable int semester,
            @RequestParam String year) {
        return drawReportService.getBySemester(semester, year);
    }

    @PutMapping("/{id}")
    public DrawReport updateReport(@PathVariable Long id, @RequestBody DrawReport drawReport) {
        // TODO: process PUT request

        return drawReportService.updateReport(id, drawReport);
    }

    @DeleteMapping("{id}")
    public void deleteReport(@PathVariable Long id) {
        drawReportService.deleteReport(id);
    }

}
