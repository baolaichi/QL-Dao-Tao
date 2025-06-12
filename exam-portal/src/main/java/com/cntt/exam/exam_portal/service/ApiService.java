package com.cntt.exam.exam_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cntt.exam.exam_portal.dto.DrawReportDTO;
import com.cntt.exam.exam_portal.dto.ExamCombinationDTO;
import com.cntt.exam.exam_portal.dto.ExamStructureDTO;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {

    private final String GATEWAY_BASE_URL = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    // ------------------- STRUCTURE -------------------
    public List<ExamStructureDTO> fetchStructures(int semester, String year) {
        String url = GATEWAY_BASE_URL + "/api/structures/semester/" + semester + "?year=" + year;
        ResponseEntity<ExamStructureDTO[]> response = restTemplate.getForEntity(url, ExamStructureDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Lấy tất cả cấu trúc đề thi (không lọc kỳ và năm)
    public List<ExamStructureDTO> fetchAllStructures() {
        String url = GATEWAY_BASE_URL + "/api/structures"; // Endpoint này cần hỗ trợ trả về toàn bộ
        ResponseEntity<ExamStructureDTO[]> response = restTemplate.getForEntity(url, ExamStructureDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public void saveStructure(ExamStructureDTO dto) {
        String url = GATEWAY_BASE_URL + "/api/structures";
        HttpEntity<ExamStructureDTO> request = new HttpEntity<>(dto);
        restTemplate.postForEntity(url, request, Void.class);
    }

    // ------------------- COMBINATION -------------------
    public List<ExamCombinationDTO> fetchCombinations(int semester, String year) {
        String url = GATEWAY_BASE_URL + "/api/combinations/semester/" + semester + "?year=" + year;
        ResponseEntity<ExamCombinationDTO[]> response = restTemplate.getForEntity(url, ExamCombinationDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public void saveCombination(ExamCombinationDTO dto) {
        String url = GATEWAY_BASE_URL + "/api/combinations";
        HttpEntity<ExamCombinationDTO> request = new HttpEntity<>(dto);
        restTemplate.postForEntity(url, request, Void.class);
    }

    // ------------------- DRAW REPORT -------------------
    public List<DrawReportDTO> fetchDrawReports(int semester, String year) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports/semester/" + semester + "?year=" + year;
        ResponseEntity<DrawReportDTO[]> response = restTemplate.getForEntity(url, DrawReportDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public void saveDrawReport(DrawReportDTO dto) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports";
        HttpEntity<DrawReportDTO> request = new HttpEntity<>(dto);
        restTemplate.postForEntity(url, request, Void.class);
    }

    // ------------------- COMBINATION -------------------
    public byte[] downloadCombinationReport(Long id) {
        String url = GATEWAY_BASE_URL + "/api/combinations/export/" + id;
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        return response.getBody();
    }

    public byte[] downloadDrawReport(int semester, String year) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports/export?semester=" + semester + "&year=" + year;
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        return response.getBody();
    }

    // Lấy một cấu trúc đề thi theo ID
    public ExamStructureDTO getStructureById(Long id) {
        String url = GATEWAY_BASE_URL + "/api/structures/" + id;
        return restTemplate.getForObject(url, ExamStructureDTO.class);
    }

    // Cập nhật cấu trúc đề thi
    public void updateStructure(ExamStructureDTO dto) {
        String url = GATEWAY_BASE_URL + "/api/structures/" + dto.getId();
        HttpEntity<ExamStructureDTO> request = new HttpEntity<>(dto);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    // Xóa cấu trúc đề thi
    public void deleteStructure(Long id) {
        String url = GATEWAY_BASE_URL + "/api/structures/" + id;
        restTemplate.delete(url);
    }

    // Lấy một báo cáo bốc thăm theo ID
    public DrawReportDTO getDrawReportById(Long id) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports/" + id;
        return restTemplate.getForObject(url, DrawReportDTO.class);
    }

    // Cập nhật báo cáo bốc thăm
    public void updateDrawReport(Long id, DrawReportDTO dto) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports/" + id;
        HttpEntity<DrawReportDTO> request = new HttpEntity<>(dto);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    // Xóa báo cáo bốc thăm
    public void deleteDrawReport(Long id) {
        String url = GATEWAY_BASE_URL + "/api/draw-reports/" + id;
        restTemplate.delete(url);
    }

}
