<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xuất Báo Cáo</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 80px;
        }
        .report-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            text-align: center;
        }
        .export-btn {
            font-size: 16px;
            padding: 12px 24px;
            border-radius: 8px;
        }
        .fa-file-pdf {
            margin-right: 8px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="report-container">
        <h2 class="mb-4 text-primary"><i class="fas fa-chart-bar"></i> Tải Báo Cáo Cấu Trúc Đề Thi</h2>
        <p class="mb-4 text-muted">Bạn có thể tải báo cáo cấu trúc định dạng PDF tại đây:</p>
        <a href="${pageContext.request.contextPath}/report/pdf" class="btn btn-success export-btn">
            <i class="fas fa-file-pdf"></i> Tải PDF
        </a>
    </div>
</div>

<a href="/draw/export?semester=1&year=2023-2024" class="btn btn-sm btn-success">
    Tải biên bản bốc thăm
</a>

<!-- Bootstrap JS (nếu cần) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
