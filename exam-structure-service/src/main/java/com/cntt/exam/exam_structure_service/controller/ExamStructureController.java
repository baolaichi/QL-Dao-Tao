package com.cntt.exam.exam_structure_service.controller;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cntt.exam.exam_structure_service.model.ExamStructure;
import com.cntt.exam.exam_structure_service.service.ExamStructureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/structures")
@CrossOrigin
public class ExamStructureController {

    private final ExamStructureService eService;

    public ExamStructureController(ExamStructureService eService) {
        this.eService = eService;
    }

    @PostMapping
    public ExamStructure createStructure(@RequestBody ExamStructure entity) {
        return eService.addExamStructure(entity);
    }

    @GetMapping
    public List<ExamStructure> getAll() {
        return eService.getAll();
    }

    @GetMapping("/semester/{semester}")
    public List<ExamStructure> getBySemester(@RequestParam String year,
            @PathVariable int semester) {
        return eService.getBySemester(semester, year);
    }

    @PutMapping("/{id}")
    public ExamStructure updateExamStructure(@PathVariable Long id, @RequestBody ExamStructure e) {
        // TODO: process PUT request

        return eService.updateExamStructure(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eService.deleteExamStructure(id);
    }

    @GetMapping("/report/pdf")
    public ResponseEntity<ByteArrayResource> getReportPdf(
            @RequestParam int semester,
            @RequestParam String year) {

        byte[] pdf = eService.generateReportPdf(semester, year);
        ByteArrayResource resource = new ByteArrayResource(pdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_hk" + semester + ".pdf")
                .contentLength(pdf.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/{id}")
    public ExamStructure getStructureById(@PathVariable Long id) {
        return eService.getById(id);
    }

    @GetMapping("/report/pdf/all")
    public ResponseEntity<ByteArrayResource> getAllStructuresPdf() {
        byte[] pdf = eService.generateAllStructuresPdf(); // gọi service tạo toàn bộ PDF
        ByteArrayResource resource = new ByteArrayResource(pdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_all.pdf")
                .contentLength(pdf.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/report/word/all")
    public ResponseEntity<ByteArrayResource> getAllStructuresAsWord() {
        byte[] docx = eService.exportAllStructuresAsWord();
        ByteArrayResource resource = new ByteArrayResource(docx);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=baocao_cautruc.docx")
                .contentLength(docx.length)
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(resource);
    }

}
