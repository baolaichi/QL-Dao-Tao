package com.cntt.exam.draw_report_service.service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.xwpf.usermodel.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.cntt.exam.draw_report_service.model.DrawReport;
import com.cntt.exam.draw_report_service.repository.DrawReportRepository;

@Service
public class DrawReportServiceImpl implements DrawReportService {
    private final DrawReportRepository drawReportRepository;

    public DrawReportServiceImpl(DrawReportRepository drawReportRepository) {
        this.drawReportRepository = drawReportRepository;
    }

    @Override
    public DrawReport addReport(DrawReport drawReport) {
        return drawReportRepository.save(drawReport);
    }

    @Override
    public List<DrawReport> getAll() {
        return drawReportRepository.findAll();
    }

    @Override
    public List<DrawReport> getBySemester(int semester, String year) {
        return drawReportRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public DrawReport updateReport(Long id, DrawReport drawReport) {
        DrawReport entityReport = drawReportRepository.findById(id).orElseThrow();
        entityReport.setSubjectName(drawReport.getSubjectName());
        entityReport.setSubjectCode(drawReport.getSubjectCode());
        entityReport.setSemester(drawReport.getSemester());
        entityReport.setAcademicYear(drawReport.getAcademicYear());
        entityReport.setExamDate(drawReport.getExamDate());
        entityReport.setExamShift(drawReport.getExamShift());
        entityReport.setNumberOfQuestions(drawReport.getNumberOfQuestions());
        entityReport.setNote(drawReport.getNote());

        return drawReportRepository.save(entityReport);
    }

    @Override
    public void deleteReport(Long id) {
        drawReportRepository.deleteById(id);
    }

    @Override
    public byte[] exportReport(int semester, String year) throws IOException {
        List<DrawReport> reports = drawReportRepository.findBySemesterAndAcademicYear(semester, year);
        return exportDrawReport(reports, semester, year);
    }

    @Override
    public byte[] exportDrawReport(List<DrawReport> reports, int semester, String academicYear) throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // Tiêu đề quốc hiệu
        XWPFParagraph header = doc.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun hRun = header.createRun();
        hRun.setBold(true);
        hRun.setFontSize(12);
        hRun.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
        hRun.addBreak();
        hRun.setText("Độc lập – Tự do – Hạnh phúc");
        hRun.addBreak();
        hRun.addBreak();

        // Tiêu đề biên bản
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(14);
        titleRun.setText("BIÊN BẢN BỐC THĂM ĐỀ THI KẾT THÚC HỌC PHẦN");
        titleRun.addBreak();
        titleRun.setText("HỌC KỲ " + semester + " ;  NĂM HỌC " + academicYear);
        titleRun.addBreak();

        // Nội dung mở đầu
        XWPFParagraph intro = doc.createParagraph();
        XWPFRun introRun = intro.createRun();
        introRun.setText("Hôm nay, ngày…. tháng… năm……, vào lúc:…., tại Văn phòng BM CNTT");
        introRun.addBreak();
        introRun.setText("Chúng tôi gồm:");
        introRun.addBreak();
        introRun.setText("1. Ông: Nguyễn Văn Huy - Chức vụ: Trưởng Bộ môn");
        introRun.addBreak();
        introRun.setText("2. Ông: Nghiêm Văn Tính - Chức vụ: Phó trưởng Bộ môn");
        introRun.addBreak();
        introRun.setText("Tiến hành bốc thăm đề thi, kết quả như sau:");
        introRun.addBreak();

        // Tạo bảng kết quả bốc thăm
        XWPFTable table = doc.createTable();

        // Header table
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("TT");
        headerRow.addNewTableCell().setText("Mã HP");
        headerRow.addNewTableCell().setText("Tên HP");
        headerRow.addNewTableCell().setText("Ca thi");
        headerRow.addNewTableCell().setText("Ngày thi");
        headerRow.addNewTableCell().setText("Mã đề thi bốc thăm");
        headerRow.addNewTableCell().setText("Số lượng đề/ca");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < reports.size(); i++) {
            DrawReport r = reports.get(i);
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(String.valueOf(i + 1));
            row.getCell(1).setText(r.getSubjectCode());
            row.getCell(2).setText(r.getSubjectName());
            row.getCell(3).setText(String.valueOf(r.getExamShift()));
            row.getCell(4).setText(r.getExamDate().format(formatter));
            row.getCell(5).setText("nt"); // mã đề thi bốc thăm (có thể thay đổi nếu cần)
            row.getCell(6).setText(String.valueOf(r.getNumberOfQuestions()));
        }

        // Kết luận + chữ ký
        XWPFParagraph footer = doc.createParagraph();
        footer.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun footerRun = footer.createRun();
        footerRun.addBreak();
        footerRun.setText("Biên bản được lập thành 01 bản được lưu tại bộ môn.");
        footerRun.addBreak();
        footerRun.addBreak();
        footerRun.setText("CÁN BỘ BỐC THĂM");
        footerRun.addBreak();
        footerRun.setText("Nghiêm Văn Tính");
        footerRun.addBreak();
        footerRun.setText("TRƯỞNG BỘ MÔN");
        footerRun.addBreak();
        footerRun.setText("Nguyễn Văn Huy");

        // Xuất file
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        doc.write(out);
        doc.close();
        return out.toByteArray();
    }

    @Override
    public DrawReport getById(Long id) {
        return drawReportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Report not found with id " + id));
    }
    
}
