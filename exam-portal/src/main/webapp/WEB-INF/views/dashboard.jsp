<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chính</title>
    <meta charset="UTF-8">
    <style>
        * {
            box-sizing: border-box;
        }

        html, body {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
            background-color: #f1f3f6;
        }

        .navbar {
            background-color: #3b82f6;
            color: white;
            padding: 12px 24px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar .left {
            font-weight: bold;
        }

        .navbar .right a {
            color: white;
            text-decoration: none;
            margin-left: 18px;
            font-weight: 500;
        }

        .navbar .right a:hover {
            text-decoration: underline;
        }

        .main-content {
            flex: 1;
            padding: 60px 30px;
            text-align: center;
        }

        .main-content h2 {
            font-size: 24px;
            margin-bottom: 12px;
            color: #222;
        }

        .main-content p {
            color: #555;
            font-size: 16px;
        }

        footer {
            background-color: #2d2f33;
            color: white;
            text-align: center;
            padding: 16px 0;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="navbar">
    <div class="left">
        Xin chào, ${sessionScope.username} [${sessionScope.role}]
    </div>
    <div class="right">
        <c:if test="${sessionScope.role == 'GIANGVIEN' || sessionScope.role == 'ADMIN'}">
            <a href="/structure">Cấu trúc đề thi</a>
            <a href="/combination">Tổ hợp đề thi</a>
            <a href="/draw">Biên bản bốc thăm</a>
        </c:if>

        <c:if test="${sessionScope.role == 'THANHTRA' || sessionScope.role == 'ADMIN'}">
            <a href="/report/" class="btn btn-success">📊 Xuất Báo cáo</a>
        </c:if>

        <c:if test="${sessionScope.role == 'ADMIN'}">
             <a href="/admin/users">Quản lý người dùng</a>
        </c:if>

        <a href="/logout">Đăng xuất</a>
    </div>
</div>

<div class="main-content">
    <h2>Chào mừng đến với hệ thống quản lý đề thi</h2>
    <p>Vui lòng chọn một chức năng từ thanh điều hướng ở trên để tiếp tục làm việc.</p>
</div>

<footer>
    © 2025 Hệ thống Quản lý Đề thi. Phát triển bởi Lại Chí Bảo.
</footer>

</body>
</html>
