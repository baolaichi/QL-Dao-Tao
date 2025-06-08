<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Đề thi</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
        }
        .navbar {
            margin-bottom: 0 !important;
            padding-top: 0.5rem;
            padding-bottom: 0.5rem;
        }
        .navbar-brand {
            font-weight: bold;
        }
        .nav-link {
            color: white !important;
            margin-right: 15px;
        }
        .nav-link:hover {
            text-decoration: underline;
        }
        .username {
            color: white;
            margin-right: 10px;
        }
        .btn-logout {
            border: 1px solid white;
            color: white;
            background-color: transparent;
        }
        .btn-logout:hover {
            background-color: #f8f9fa;
            color: #000;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <!-- Logo & tên hệ thống -->
        <a class="navbar-brand" href="/"><i class="bi bi-journal-text"></i> Quản lý Đề thi</a>

        <!-- Menu điều hướng -->
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${sessionScope.role == 'GIANGVIEN' || sessionScope.role == 'ADMIN'}">
                    <li class="nav-item"><a class="nav-link" href="/structure">Cấu trúc đề thi</a></li>
                    <li class="nav-item"><a class="nav-link" href="/combination">Tổ hợp đề thi</a></li>
                    <li class="nav-item"><a class="nav-link" href="/draw">Biên bản bốc thăm</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 'THANHTRA' || sessionScope.role == 'ADMIN'}">
                    <li class="nav-item"><a class="nav-link" href="/report/">📊 Xuất Báo cáo</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item"><a class="nav-link" href="/admin/users">Quản lý người dùng</a></li>
                </c:if>
            </ul>

            <!-- Thông tin người dùng & nút đăng xuất -->
            <div class="d-flex align-items-center">
                <span class="username">
                    <i class="bi bi-person-circle"></i>
                    ${sessionScope.username} [${sessionScope.role}]
                </span>
                <a class="btn btn-sm btn-logout" href="/logout">Đăng xuất</a>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
