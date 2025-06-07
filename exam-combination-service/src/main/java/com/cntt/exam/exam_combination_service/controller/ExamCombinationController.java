package com.cntt.exam.exam_combination_service.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.cntt.exam.exam_combination_service.model.ExamCombination;
import com.cntt.exam.exam_combination_service.repository.ExamCombinationRepository;
import com.cntt.exam.exam_combination_service.service.ExamCombinationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/combinations")
@CrossOrigin
public class ExamCombinationController {
    private ExamCombinationService eService;
    @Autowired
    private ExamCombinationRepository examCombinationRepository;

    public ExamCombinationController(ExamCombinationService eService) {
        this.eService = eService;
    }

    @PostMapping
    public ExamCombination createCombination(@RequestBody ExamCombination e) {
        // TODO: process POST request

        return eService.addCombination(e);
    }

    @GetMapping
    public List<ExamCombination> getAll() {
        return eService.getAll();
    }

    @GetMapping("/semester/{semester}")
    public List<ExamCombination> getBySemester(@PathVariable int semester,
            @RequestParam String year) {
        return eService.getBySemesterAndYear(semester, year);
    }

    @PutMapping("/{id}")
    public ExamCombination putMethodName(@PathVariable Long id, @RequestBody ExamCombination e) {
        // TODO: process PUT request

        return eService.updateCombination(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eService.delete(id);
    }

    @GetMapping("/export/{id}")
    public ResponseEntity<byte[]> exportReport(@PathVariable Long id) throws IOException {
        // Lấy ExamCombination từ service
        ExamCombination exam = eService.getAll()
                .stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tổ hợp đề thi"));

        // Gọi đúng method generateReport từ service
        byte[] reportBytes = eService.generateReport(exam);

        // Tạo header để trả về file .docx
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("tohop_dethi_" + id + ".docx")
                .build());

        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    }
}
