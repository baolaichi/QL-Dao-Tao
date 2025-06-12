<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chỉnh sửa Biên bản bốc thăm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4>Chỉnh sửa Biên bản bốc thăm</h4>
        </div>
        <div class="card-body">
            <form action="/draw/update" method="post">
                <input type="hidden" name="id" value="${drawForm.id}" />

                <div class="mb-3">
                    <label class="form-label">Môn học</label>
                    <input type="text" name="subjectName" class="form-control" value="${drawForm.subjectName}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Mã môn</label>
                    <input type="text" name="subjectCode" class="form-control" value="${drawForm.subjectCode}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Kỳ</label>
                    <input type="text" name="semester" class="form-control" value="${drawForm.semester}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Năm học</label>
                    <input type="text" name="academicYear" class="form-control" value="${drawForm.academicYear}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ngày thi</label>
                    <input type="date" name="examDate" class="form-control" value="${drawForm.examDate}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ca thi</label>
                    <input type="text" name="examShift" class="form-control" value="${drawForm.examShift}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Số đề</label>
                    <input type="number" name="numberOfQuestions" class="form-control" value="${drawForm.numberOfQuestions}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ghi chú</label>
                    <input type="text" name="note" class="form-control" value="${drawForm.note}">
                </div>

                <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> Cập nhật</button>
                <a href="/draw" class="btn btn-secondary"><i class="bi bi-x-circle"></i> Hủy</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
