package com.cntt.exam.exam_combination_service.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cntt.exam.exam_combination_service.model.ExamCombination;
import com.cntt.exam.exam_combination_service.repository.ExamCombinationRepository;

@Service
public class ExamCombinationServiceImpl implements ExamCombinationService {
    private final ExamCombinationRepository eRepository;

    public ExamCombinationServiceImpl(ExamCombinationRepository eRepository) {
        this.eRepository = eRepository;
    }

    @Override
    public ExamCombination addCombination(ExamCombination e) {
        return eRepository.save(e);
    }

    @Override
    public List<ExamCombination> getAll() {
        return eRepository.findAll();
    }

    @Override
    public List<ExamCombination> getBySemesterAndYear(int semester, String year) {
        return eRepository.findBySemesterAndAcademicYear(semester, year);
    }

    @Override
    public ExamCombination updateCombination(Long id, ExamCombination e) {
        ExamCombination exam = eRepository.findById(id).orElseThrow();
        exam.setSubjectName(e.getSubjectName());
        exam.setSubjectCode(e.getSubjectCode());
        exam.setExamType(e.getExamType());
        exam.setSemester(e.getSemester());
        exam.setAcademicYear(e.getAcademicYear());
        exam.setQuestionCodes(e.getQuestionCodes());
        exam.setNote(e.getNote());

        return eRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public byte[] generateReport(ExamCombination examCombination) throws IOException {
        XWPFDocument document = new XWPFDocument();

        // Tiêu đề quốc hiệu
        XWPFParagraph title1 = document.createParagraph();
        title1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run1 = title1.createRun();
        run1.setBold(true);
        run1.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
        run1.addBreak();
        run1.setText("Độc lập – Tự do – Hạnh phúc");
        run1.addBreak();

        // Ngày tháng
        XWPFParagraph datePara = document.createParagraph();
        datePara.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun dateRun = datePara.createRun();
        LocalDate now = LocalDate.now();
        dateRun.setText(
                "Thái Nguyên, ngày " + now.getDayOfMonth() + " tháng " + now.getMonthValue() + " năm " + now.getYear());

        // Tên báo cáo
        XWPFParagraph header = document.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun headerRun = header.createRun();
        headerRun.setBold(true);
        headerRun.setFontSize(14);
        headerRun.setText("BẢNG TỔ HỢP ĐỀ THI KẾT THÚC HỌC PHẦN");

        // Thông tin học phần
        XWPFParagraph info = document.createParagraph();
        XWPFRun infoRun = info.createRun();
        infoRun.setText("Tên học phần: " + examCombination.getSubjectName() + "\t\tMã học phần: "
                + examCombination.getSubjectCode());
        infoRun.addBreak();
        infoRun.setText("Hình thức thi: " + examCombination.getExamType());
        infoRun.addBreak();
        infoRun.setText("Số tín chỉ: 02\t\tHọc kỳ: " + examCombination.getSemester() + "\t Năm học: "
                + examCombination.getAcademicYear());
        infoRun.addBreak();
        infoRun.setText("Thời gian làm bài: 60 phút");
        infoRun.addBreak();
        infoRun.setText("Kết cấu cụ thể: mỗi đề thi gồm có 3 câu:");

        // Bảng mã đề
        XWPFTable table = document.createTable();
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("Stt");
        headerRow.addNewTableCell().setText("Câu 1");
        headerRow.addNewTableCell().setText("Câu 2");
        headerRow.addNewTableCell().setText("Câu 3");

        List<String> questionCodes = examCombination.getQuestionCodes();
        int rows = questionCodes.size() / 3;

        for (int i = 0; i < rows; i++) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(String.format("%02d", i + 1));
            row.getCell(1).setText(questionCodes.get(i * 3));
            row.getCell(2).setText(questionCodes.get(i * 3 + 1));
            row.getCell(3).setText(questionCodes.get(i * 3 + 2));
        }

        // Ghi chú + Trưởng bộ môn
        XWPFParagraph footer = document.createParagraph();
        footer.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun footerRun = footer.createRun();
        footerRun.addBreak();
        footerRun.setText("P. Trưởng bộ môn");
        footerRun.addBreak();
        footerRun.setText("TS. Nghiêm Văn Tính");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();

        return out.toByteArray();
    }
}
