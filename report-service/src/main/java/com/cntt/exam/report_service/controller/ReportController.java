package com.cntt.exam.report_service.controller;

import org.springframework.http.MediaType;

import org.springframework.http.HttpHeaders;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cntt.exam.report_service.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestParam int semester, @RequestParam String year) throws Exception {
        ByteArrayOutputStream pdf = reportService.generatePdfReport(semester, year);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_hk" + semester + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.toByteArray());
    }
}
