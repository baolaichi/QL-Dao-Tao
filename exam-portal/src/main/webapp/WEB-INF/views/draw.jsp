<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Biên bản bốc thăm</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 30px;
        }

        .form-section {
            background-color: #fff;
            padding: 24px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .form-section h2,
        .form-section h3 {
            margin-bottom: 20px;
        }

        .btn-custom {
            background-color: #007bff;
            color: white;
            font-weight: 500;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .table-container {
            background-color: #fff;
            padding: 24px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .table th {
            background-color: #007bff;
            color: white;
        }

        .back-btn {
            margin-top: 20px;
        }

        .form-label {
            font-weight: 500;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container">
    <!-- Nhập Biên bản -->
    <div class="form-section">
        <h2>Nhập Biên bản bốc thăm</h2>
        <form action="/draw/save" method="post">
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Môn học</label>
                    <input type="text" class="form-control" name="subjectName" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Mã môn</label>
                    <input type="text" class="form-control" name="subjectCode" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Kỳ</label>
                    <input type="text" class="form-control" name="semester" value="3" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Năm học</label>
                    <input type="text" class="form-control" name="academicYear" value="2023-2024" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Ngày thi</label>
                    <input type="date" class="form-control" name="examDate" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Ca thi</label>
                    <input type="text" class="form-control" name="examShift" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Số đề</label>
                    <input type="number" class="form-control" name="numberOfQuestions" required>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Ghi chú</label>
                    <input type="text" class="form-control" name="note">
                </div>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-success"><i class="bi bi-save"></i> Lưu</button>
            </div>
        </form>
    </div>

    <!-- Tải xuống biên bản -->
    <div class="form-section">
        <h3>Tải biên bản bốc thăm</h3>
        <form action="/draw/export" method="get" class="row g-3 align-items-end">
            <div class="col-md-3">
                <label class="form-label">Kỳ</label>
                <input type="number" name="semester" value="3" class="form-control" required>
            </div>
            <div class="col-md-4">
                <label class="form-label">Năm học</label>
                <input type="text" name="year" value="2023-2024" class="form-control" required>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-custom w-100"><i class="bi bi-download"></i> Tải biên bản</button>
            </div>
        </form>
    </div>

    <!-- Danh sách -->
    <div class="table-container">
        <h3>Danh sách Biên bản bốc thăm</h3>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Môn học</th>
                    <th>Ngày thi</th>
                    <th>Số đề</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${drawReports}">
                    <tr>
                        <td>${d.subjectName}</td>
                        <td>${d.examDate}</td>
                        <td>${d.numberOfQuestions}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/dashboard" class="btn btn-outline-secondary back-btn"><i class="bi bi-arrow-left"></i> Quay lại trang chủ</a>
    </div>
</div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
