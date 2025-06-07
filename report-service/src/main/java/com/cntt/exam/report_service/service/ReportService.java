package com.cntt.exam.report_service.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    public ByteArrayOutputStream generateExcelReport(int semester, String year) throws Exception {
        List<ExamStructureDTO> structures = fetchStructures(semester, year);
        List<ExamCombinationDTO> combinations = fetchCombinations(semester, year);
        List<DrawReportDTO> draws = fetchDraws(semester, year);

        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // Sheet 1: Cấu trúc đề thi
        XSSFSheet structureSheet = workbook.createSheet("Cấu trúc đề thi");
        Row structureHeader = structureSheet.createRow(0);
        structureHeader.createCell(0).setCellValue("Môn học");
        structureHeader.createCell(1).setCellValue("Cấu trúc");
        
        int rowIndex = 1;
        for (var s : structures) {
            Row row = structureSheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(s.getSubjectName());
            row.createCell(1).setCellValue(s.getStructureDescription());
        }

        // Sheet 2: Tổ hợp đề thi
        XSSFSheet combinationSheet = workbook.createSheet("Tổ hợp đề thi");
        Row combinationHeader = combinationSheet.createRow(0);
        combinationHeader.createCell(0).setCellValue("Môn học");
        combinationHeader.createCell(1).setCellValue("Mã đề");
        
        rowIndex = 1;
        for (var c : combinations) {
            Row row = combinationSheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(c.getSubjectName());
            row.createCell(1).setCellValue(String.join(", ", c.getQuestionCodes()));
        }

        // Sheet 3: Biên bản bốc thăm
        XSSFSheet drawSheet = workbook.createSheet("Biên bản bốc thăm");
        Row drawHeader = drawSheet.createRow(0);
        drawHeader.createCell(0).setCellValue("Môn học");
        drawHeader.createCell(1).setCellValue("Ngày thi");
        drawHeader.createCell(2).setCellValue("Ca thi");
        drawHeader.createCell(3).setCellValue("Số đề");
        
        rowIndex = 1;
        for (var d : draws) {
            Row row = drawSheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(d.getSubjectName());
            row.createCell(1).setCellValue(d.getExamDate().toString());
            row.createCell(2).setCellValue(d.getExamShift());
            row.createCell(3).setCellValue(d.getNumberOfQuestions());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
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
