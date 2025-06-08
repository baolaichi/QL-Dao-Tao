<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Nhập Cấu trúc đề thi</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h3 class="card-title mb-4">Nhập Cấu trúc đề thi</h3>
            <form action="/structure/save" method="post">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Môn học</label>
                        <input type="text" name="subjectName" class="form-control" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Mã môn</label>
                        <input type="text" name="subjectCode" class="form-control" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Loại đề</label>
                        <input type="text" name="examType" class="form-control" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Kỳ</label>
                        <input type="text" name="semester" value="3" class="form-control" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Năm học</label>
                        <input type="text" name="academicYear" value="2023-2024" class="form-control" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Mức độ</label>
                        <input type="text" name="difficultyLevel" class="form-control" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Mô tả</label>
                        <input type="text" name="structureDescription" class="form-control" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-success">
                    <i class="fa fa-save"></i> Lưu
                </button>
            </form>
        </div>
    </div>

    <div class="mt-5">
        <h4>Danh sách Cấu trúc đề thi</h4>
        <table class="table table-bordered table-hover bg-white mt-3 shadow-sm">
            <thead class="thead-dark">
                <tr>
                    <th>Môn học</th>
                    <th>Mã môn</th>
                    <th>Loại đề</th>
                    <th>Kỳ</th>
                    <th>Năm học</th>
                    <th>Mức độ</th>
                    <th>Mô tả</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${structures}">
                    <tr>
                        <td>${s.subjectName}</td>
                        <td>${s.subjectCode}</td>
                        <td>${s.examType}</td>
                        <td>${s.semester}</td>
                        <td>${s.academicYear}</td>
                        <td>${s.difficultyLevel}</td>
                        <td>${s.structureDescription}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/dashboard" class="btn btn-primary mt-3">← Quay lại trang chủ</a>
    </div>
</div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
<!-- Font Awesome (for save icon) -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
