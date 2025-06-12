package com.cntt.exam.exam_structure_service.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cntt.exam.exam_structure_service.model.ExamStructure;
import com.cntt.exam.exam_structure_service.repository.ExamStructureRepository;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;

import org.apache.poi.xwpf.usermodel.*;

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

    @Override
    public ExamStructure getById(Long id) {
        return eRepository.findById(id).orElse(null);
    }

    @Override
    public byte[] generateAllStructuresPdf() {
        List<ExamStructure> structures = eRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);

            // ⚠️ Fix: Không khởi tạo Document 2 lần
            Document document;

            // 👇 Load font Unicode tiếng Việt từ resources/fonts/times.ttf
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/times.ttf");
            if (fontStream == null)
                throw new RuntimeException("Không tìm thấy file font times.ttf trong /resources/fonts/");
            byte[] fontBytes = fontStream.readAllBytes();
            com.itextpdf.io.font.FontProgram fontProgram = com.itextpdf.io.font.FontProgramFactory
                    .createFont(fontBytes);
            PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H);

            document = new Document(pdf);
            document.setFont(font); // áp dụng font Unicode tiếng Việt

            for (ExamStructure s : structures) {
                // Quốc hiệu, tiêu ngữ
                document.add(new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM")
                        .setBold().setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("Độc lập – Tự do – Hạnh phúc")
                        .setItalic().setTextAlignment(TextAlignment.CENTER).setMarginBottom(10));

                // Khoa và bộ môn
                document.add(new Paragraph("KHOA ĐIỆN TỬ").setBold());
                document.add(new Paragraph("BỘ MÔN: CNTT").setBold().setMarginBottom(10));

                // Tiêu đề
                document.add(new Paragraph("BẢNG CẤU TRÚC ĐỀ THI KẾT THÚC HỌC PHẦN")
                        .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER).setMarginBottom(15));

                // Thông tin học phần
                document.add(new Paragraph(
                        "Tên học phần: " + s.getSubjectName() + "   ;   Mã học phần: " + s.getSubjectCode()));
                document.add(new Paragraph("Số tín chỉ: " + (s.getCredit() != null ? s.getCredit() : "3")
                        + "        Hình thức thi: " + s.getExamType()));
                document.add(new Paragraph("Thời gian làm bài: " + s.getDuration() + " phút"));
                document.add(new Paragraph("Học kỳ: " + s.getSemester() + "        Năm học: " + s.getAcademicYear())
                        .setMarginBottom(15));

                // Mục I: Cấu trúc đề thi
                document.add(new Paragraph("I. CẤU TRÚC ĐỀ THI:").setBold());

                Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 3, 2, 2, 2, 2, 2 }))
                        .useAllAvailableWidth();

                table.addHeaderCell(new Cell().add(new Paragraph("STT").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Câu hỏi").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Nhớ").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Hiểu").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Vận dụng").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Phân tích").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Sáng tạo").setBold()));

                // Phân tích câu hỏi từ structureDescription
                String[] questions = s.getStructureDescription().split("\\r?\\n|\\.");
                int stt = 1;
                for (String q : questions) {
                    if (q.trim().isEmpty())
                        continue;

                    table.addCell(String.valueOf(stt++));
                    table.addCell(new Paragraph(q.trim()));
                    table.addCell(q.toLowerCase().contains("nhớ") ? "x" : "");
                    table.addCell(q.toLowerCase().contains("hiểu") ? "x" : "");
                    table.addCell(q.toLowerCase().contains("vận dụng") ? "x" : "");
                    table.addCell(q.toLowerCase().contains("phân tích") ? "x" : "");
                    table.addCell(q.toLowerCase().contains("sáng tạo") ? "x" : "");
                }

                document.add(table);

                // Mục II. Nội dung
                document.add(new Paragraph("\nII. NỘI DUNG").setBold().setMarginTop(10));
                document.add(new Paragraph("Đề thi gồm " + (stt - 1) + " câu/phần, phân bố như sau:"));
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].trim().isEmpty())
                        continue;
                    document.add(new Paragraph("Câu " + (i + 1) + ": " + questions[i].trim()));
                }

                // Ngày tháng, chữ ký
                document.add(new Paragraph("\n\nThái Nguyên, ngày ... tháng ... năm 2024")
                        .setTextAlignment(TextAlignment.RIGHT));
                document.add(new Paragraph("P. TRƯỞNG BỘ MÔN").setBold()
                        .setTextAlignment(TextAlignment.RIGHT).setMarginBottom(30));
                document.add(new Paragraph("TS. Nghiêm Văn Tính").setTextAlignment(TextAlignment.RIGHT));

                document.add(new AreaBreak()); // Xuống trang mới
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public byte[] exportAllStructuresAsWord() {
        List<ExamStructure> structures = eRepository.findAll();

        try (XWPFDocument doc = new XWPFDocument(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            for (ExamStructure s : structures) {
                // Quốc hiệu – tiêu ngữ
                XWPFParagraph heading = doc.createParagraph();
                heading.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run = heading.createRun();
                run.setBold(true);
                run.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
                run.addBreak();
                run.setText("Độc lập – Tự do – Hạnh phúc");
                run.addBreak();
                run.addBreak();

                // Thông tin chung
                XWPFParagraph info = doc.createParagraph();
                info.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run2 = info.createRun();
                run2.setText("KHOA ĐIỆN TỬ");
                run2.addBreak();
                run2.setText("BỘ MÔN: CNTT");
                run2.addBreak();
                run2.addBreak();
                run2.setBold(true);
                run2.setText("BẢNG CẤU TRÚC ĐỀ THI KẾT THÚC HỌC PHẦN");
                run2.addBreak();
                run2.setBold(false);
                run2.setText("Tên học phần: " + s.getSubjectName() + "   ;   Mã học phần: " + s.getSubjectCode());
                run2.addBreak();
                run2.setText("Số tín chỉ: " + s.getCredit() + "   ;   Hình thức thi: " + s.getExamType());
                run2.addBreak();
                run2.setText("Thời gian làm bài: " + s.getDuration() + " phút");
                run2.addBreak();
                run2.setText("Học kỳ: " + s.getSemester() + " ; Năm học: " + s.getAcademicYear());
                run2.addBreak();
                run2.addBreak();

                // Tiêu đề bảng
                XWPFParagraph tableTitle = doc.createParagraph();
                XWPFRun run3 = tableTitle.createRun();
                run3.setText("I. CẤU TRÚC ĐỀ THI:");
                run3.setBold(true);
                run3.addBreak();

                // Bảng câu hỏi
                XWPFTable table = doc.createTable();
                XWPFTableRow header = table.getRow(0);
                header.getCell(0).setText("STT");
                header.addNewTableCell().setText("Câu hỏi");
                header.addNewTableCell().setText("Nhớ");
                header.addNewTableCell().setText("Hiểu");
                header.addNewTableCell().setText("Vận dụng");
                header.addNewTableCell().setText("Phân tích");
                header.addNewTableCell().setText("Sáng tạo");

                String[] questions = s.getStructureDescription().split("\\r?\\n|\\.");
                int stt = 1;
                for (String q : questions) {
                    if (q.trim().isEmpty())
                        continue;
                    XWPFTableRow row = table.createRow();
                    row.getCell(0).setText(String.valueOf(stt++));
                    row.getCell(1).setText(q.trim());
                    row.getCell(2).setText(q.toLowerCase().contains("nhớ") ? "x" : "");
                    row.getCell(3).setText(q.toLowerCase().contains("hiểu") ? "x" : "");
                    row.getCell(4).setText(q.toLowerCase().contains("vận dụng") ? "x" : "");
                    row.getCell(5).setText(q.toLowerCase().contains("phân tích") ? "x" : "");
                    row.getCell(6).setText(q.toLowerCase().contains("sáng tạo") ? "x" : "");
                }

                // Mục II
                XWPFParagraph contentTitle = doc.createParagraph();
                contentTitle.setSpacingBefore(500);
                XWPFRun contentRun = contentTitle.createRun();
                contentRun.setText("\nII. NỘI DUNG");
                contentRun.setBold(true);
                contentRun.addBreak();
                contentRun.setText("Đề thi gồm " + (stt - 1) + " câu/phần, phân bố như sau:");
                contentRun.addBreak();

                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].trim().isEmpty())
                        continue;
                    contentRun.setText("Câu " + (i + 1) + ": " + questions[i].trim());
                    contentRun.addBreak();
                }

                // Chữ ký
                XWPFParagraph sign = doc.createParagraph();
                sign.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun signRun = sign.createRun();
                signRun.addBreak();
                signRun.addBreak();
                signRun.setText("Thái Nguyên, ngày ... tháng ... năm 2024");
                signRun.addBreak();
                signRun.setText("P. TRƯỞNG BỘ MÔN");
                signRun.setBold(true);
                signRun.addBreak();
                signRun.addBreak();
                signRun.setBold(false);
                signRun.setText("TS. Nghiêm Văn Tính");

                // Trang mới
                doc.createParagraph().setPageBreak(true);
            }

            doc.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

}
