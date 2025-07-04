package com.cntt.exam.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import com.cntt.exam.exam_portal.dto.DrawReportDTO;
import com.cntt.exam.exam_portal.service.ApiService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/draw")
public class DrawController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String showDrawPage(
            HttpSession session,
            @RequestParam(defaultValue = "3") int semester,
            @RequestParam(defaultValue = "2023-2024") String year,
            Model model) {

        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        List<DrawReportDTO> list = apiService.fetchDrawReports(semester, year);
        model.addAttribute("drawReports", list);
        model.addAttribute("drawForm", new DrawReportDTO());
        return "draw";
    }

    @PostMapping("/save")
    public String saveDraw(@ModelAttribute("drawForm") DrawReportDTO dto,
            HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        apiService.saveDrawReport(dto);
        return "redirect:/draw";
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> downloadDrawReport(@RequestParam int semester,
            @RequestParam String year,
            HttpSession session) {
        // Nếu có kiểm tra quyền thì thêm ở đây
        byte[] file = apiService.downloadDrawReport(semester, year);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("bien_ban_boc_tham_HK" + semester + "_" + year + ".docx")
                .build());

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public String viewDrawReport(@PathVariable Long id, HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        DrawReportDTO report = apiService.getDrawReportById(id);
        model.addAttribute("draw", report);
        return "draw/view"; // JSP riêng để hiển thị chi tiết
    }

    @GetMapping("/edit/{id}")
    public String editDrawReport(@PathVariable Long id, HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        DrawReportDTO report = apiService.getDrawReportById(id);
        model.addAttribute("drawForm", report);
        return "draw/edit"; // JSP có form chỉnh sửa
    }

    @PostMapping("/update/{id}")
    public String updateDrawReport(@PathVariable Long id,
            @ModelAttribute("drawForm") DrawReportDTO dto,
            HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        apiService.updateDrawReport(id, dto);
        return "redirect:/draw";
    }

    @PostMapping("/delete/{id}")
    public String deleteDrawReport(@PathVariable Long id, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"GIANGVIEN".equals(role) && !"ADMIN".equals(role)) {
            return "redirect:/dashboard";
        }

        apiService.deleteDrawReport(id);
        return "redirect:/draw";
    }

}
