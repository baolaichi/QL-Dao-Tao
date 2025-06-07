package com.cntt.exam.exam_structure_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.exam_structure_service.model.ExamStructure;
import com.cntt.exam.exam_structure_service.repository.ExamStructureRepository;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;

@Service
public class ExamStructureServiceImpl implements ExamStructureService {
    private final ExamStructureRepository eRepository;

    public ExamStructureServiceImpl(ExamStructureRepository eRepository) {
        this.eRepository = eRepository;
    }

    @Override
    public ExamStructure addExamStructure(ExamStructure examStructure) {
        return eRepository.save(examStructure);
    }

    @Override
    public List<ExamStructure> getAll() {
        return eRepository.findAll();
    }

    @Override
    public List<ExamStructure> getBySemester(int semester, String year) {
        return eRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public ExamStructure updateExamStructure(Long id, ExamStructure newStructure) {
        ExamStructure e = eRepository.findById(id).orElseThrow();
        e.setSubjectName(newStructure.getSubjectName());
        e.setSubjectCode(newStructure.getSubjectCode());
        e.setExamType(newStructure.getExamType());
        e.setSemester(newStructure.getSemester());
        e.setAcademicYear(newStructure.getAcademicYear());
        e.setDifficultyLevel(newStructure.getDifficultyLevel());
        e.setStructureDescription(newStructure.getStructureDescription());
        return eRepository.save(e);
    }

    @Override
    public void deleteExamStructure(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public byte[] generateReportPdf(int semester, String academicYear) {

        String fontPath = "src/main/resources/fonts/times.ttf";
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            // Tạo font với pdfDoc
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, pdfDoc);
            doc.setFont(font);

            // Lấy dữ liệu cấu trúc đề thi
            List<ExamStructure> structures = eRepository.findBySemesterAndAcademicYear(semester, academicYear);

            // Thêm nội dung cho file PDF
            doc.add(new Paragraph("BÁO CÁO CẤU TRÚC ĐỀ THI").setBold().setFontSize(16));
            doc.add(new Paragraph("Học kỳ: " + semester));
            doc.add(new Paragraph("Năm học: " + academicYear));
            doc.add(new Paragraph("Tổng số cấu trúc: " + structures.size()));
            doc.add(new Paragraph("\n"));

            Table table = new Table(6).useAllAvailableWidth();
            table.addHeaderCell("STT");
            table.addHeaderCell("Môn học");
            table.addHeaderCell("Mã môn");
            table.addHeaderCell("Hình thức thi");
            table.addHeaderCell("Mức độ");
            table.addHeaderCell("Mô tả");

            int index = 1;
            for (ExamStructure s : structures) {
                table.addCell(String.valueOf(index++));
                table.addCell(s.getSubjectName());
                table.addCell(s.getSubjectCode());
                table.addCell(s.getExamType());
                table.addCell(s.getDifficultyLevel());
                table.addCell(s.getStructureDescription());
            }

            doc.add(table);
            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu muốn có thể xử lý hoặc ném lỗi lên trên
        }

        return out.toByteArray();
    }

}
