package com.cntt.exam.report_service.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cntt.exam.report_service.dto.DrawReportDTO;
import com.cntt.exam.report_service.dto.ExamCombinationDTO;
import com.cntt.exam.report_service.dto.ExamStructureDTO;

@Service
public class ReportService {

    private RestTemplate restTemplate;

    public ReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String STRUCTURE_URL = "http://exam-structure-service/api/structures/semester/";
    private final String COMBINATION_URL = "http://exam-combination-service/api/combinations/semester/";
    private final String DRAW_URL = "http://draw-report-service/api/draw-reports/semester/";

    public ByteArrayOutputStream generatePdfReport(int semester, String year) throws Exception {
        List<ExamStructureDTO> structures = fetchStructures(semester, year);
        List<ExamCombinationDTO> combinations = fetchCombinations(semester, year);
        List<DrawReportDTO> draws = fetchDraws(semester, year);

        com.lowagie.text.Document document = new com.lowagie.text.Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        com.lowagie.text.pdf.PdfWriter.getInstance(document, out);
        document.open();

        document.add(new com.lowagie.text.Paragraph("BÁO CÁO HỒ SƠ THI - HỌC KỲ " + semester + " NĂM " + year));
        document.add(new com.lowagie.text.Paragraph(""));

        document.add(new com.lowagie.text.Paragraph("1. CẤU TRÚC ĐỀ THI"));
        for (var s : structures) {
            document.add(
                    new com.lowagie.text.Paragraph("- " + s.getSubjectName() + ": " + s.getStructureDescription()));
        }

        document.add(new com.lowagie.text.Paragraph(""));
        document.add(new com.lowagie.text.Paragraph("2. TỔ HỢP ĐỀ THI"));
        for (var c : combinations) {
            document.add(new com.lowagie.text.Paragraph("- " + c.getSubjectName() + ": " + c.getQuestionCodes()));
        }

        document.add(new com.lowagie.text.Paragraph(""));
        document.add(new com.lowagie.text.Paragraph("3. BIÊN BẢN BỐC THĂM"));
        for (var d : draws) {
            document.add(new com.lowagie.text.Paragraph("- " + d.getSubjectName() + " | Ngày thi: " + d.getExamDate()));
        }

        document.close();
        return out;
    }

    private List<ExamStructureDTO> fetchStructures(int semester, String year) {
        String url = STRUCTURE_URL + semester + "?year=" + year;
        ResponseEntity<ExamStructureDTO[]> response = restTemplate.getForEntity(url, ExamStructureDTO[].class);
        return List.of(response.getBody());
    }

    private List<ExamCombinationDTO> fetchCombinations(int semester, String year) {
        String url = COMBINATION_URL + semester + "?year=" + year;
        ResponseEntity<ExamCombinationDTO[]> response = restTemplate.getForEntity(url, ExamCombinationDTO[].class);
        return List.of(response.getBody());
    }

    private List<DrawReportDTO> fetchDraws(int semester, String year) {
        String url = DRAW_URL + semester + "?year=" + year;
        ResponseEntity<DrawReportDTO[]> response = restTemplate.getForEntity(url, DrawReportDTO[].class);
        return List.of(response.getBody());
    }

}
