package com.cntt.exam.report_service.dto;

import java.util.List;

public class ExamCombinationDTO {
    private String subjectName;
    private String subjectCode;
    private String examType;
    private int semester;
    private String academicYear;
    private List<String> questionCodes;
    private String note;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<String> getQuestionCodes() {
        return questionCodes;
    }

    public void setQuestionCodes(List<String> questionCodes) {
        this.questionCodes = questionCodes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
