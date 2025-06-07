package com.cntt.exam.draw_report_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cntt.exam.draw_report_service.model.DrawReport;
import com.cntt.exam.draw_report_service.service.DrawReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ContentDisposition;

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

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportDrawReport(@RequestParam int semester, @RequestParam String year)
            throws IOException {
        byte[] file = drawReportService.exportReport(semester, year);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("bien_ban_boc_tham_HK" + semester + "_" + year + ".docx")
                .build());

        return ResponseEntity.ok().headers(headers).body(file);
    }

}
