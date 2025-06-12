<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi tiết Biên bản bốc thăm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4>Chi tiết Biên bản bốc thăm</h4>
        </div>
        <div class="card-body">
            <dl class="row">
                <dt class="col-sm-4">Môn học</dt>
                <dd class="col-sm-8">${draw.subjectName}</dd>

                <dt class="col-sm-4">Mã môn</dt>
                <dd class="col-sm-8">${draw.subjectCode}</dd>

                <dt class="col-sm-4">Kỳ</dt>
                <dd class="col-sm-8">${draw.semester}</dd>

                <dt class="col-sm-4">Năm học</dt>
                <dd class="col-sm-8">${draw.academicYear}</dd>

                <dt class="col-sm-4">Ngày thi</dt>
                <dd class="col-sm-8">${draw.examDate}</dd>

                <dt class="col-sm-4">Ca thi</dt>
                <dd class="col-sm-8">${draw.examShift}</dd>

                <dt class="col-sm-4">Số đề</dt>
                <dd class="col-sm-8">${draw.numberOfQuestions}</dd>

                <dt class="col-sm-4">Ghi chú</dt>
                <dd class="col-sm-8">${draw.note}</dd>
            </dl>

            <a href="/draw/edit/${draw.id}" class="btn btn-warning"><i class="bi bi-pencil-square"></i> Chỉnh sửa</a>
            <a href="/draw/delete/${draw.id}" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xóa biên bản này?');">
                <i class="bi bi-trash"></i> Xóa
            </a>
            <a href="/draw" class="btn btn-secondary"><i class="bi bi-arrow-left"></i> Quay lại danh sách</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
