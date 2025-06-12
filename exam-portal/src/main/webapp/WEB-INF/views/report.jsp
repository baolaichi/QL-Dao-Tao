<%@ page contentType="text/html;charset=UTF-8" %>
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
        .form-section {
            max-width: 600px;
            margin: 40px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container">
    <div class="report-container">
        <h2 class="mb-4 text-primary"><i class="fas fa-chart-bar"></i> Tải Báo Cáo Cấu Trúc Đề Thi</h2>
        <p class="mb-4 text-muted">Bạn có thể tải báo cáo cấu trúc định dạng PDF tại đây:</p>
        <a href="${pageContext.request.contextPath}/report/pdf/all" class="btn btn-success export-btn">
            <i class="fas fa-file-pdf"></i> Tải PDF
        </a>
        <a href="/report/word/all" class="btn btn-outline-primary">Tải báo cáo cấu trúc đề thi (.docx)</a>

    </div>

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
                <button type="submit" class="btn btn-success w-100"><i class="bi bi-download"></i> Tải biên bản</button>
            </div>
        </form>
    </div>

    <!-- Bắt đầu phần tải báo cáo tổ hợp đề thi -->
    <div class="form-section">
        <h3>Tải báo cáo tổ hợp đề thi</h3>
        <form id="downloadForm" autocomplete="off" onsubmit="return redirectDownload(event)" style="display: flex; gap: 10px;">
            <select id="downloadSelect" name="id" required style="flex-grow: 1; padding: 10px; font-size: 1rem; border: 1.5px solid #ccc; border-radius: 8px;">
                <option value="" disabled selected>-- Chọn tổ hợp đề thi --</option>
                <c:forEach var="combo" items="${combinations}">
                    <option value="${combo.id}">
                        ${combo.subjectName} - ${combo.subjectCode} - ${combo.examType}
                    </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-success" style="padding: 12px 20px;">
                <i class="fas fa-download"></i> Tải báo cáo
            </button>
        </form>
    </div>
</div>

<script>
    function redirectDownload(event) {
        event.preventDefault();
        const select = document.getElementById('downloadSelect');
        const id = select.value;
        if (!id) {
            alert('Vui lòng chọn tổ hợp đề thi!');
            return false;
        }
        window.location.href = '${pageContext.request.contextPath}/combination/download/' + encodeURIComponent(id);
        return false;
    }
</script>

<!-- Bootstrap JS (nếu cần) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
