<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chính</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Thêm Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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

        /* CSS tùy chỉnh cho carousel */
        .carousel-item img {
            height: 400px; /* Giảm chiều cao để phù hợp với trang */
            object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
        }
        .carousel-caption {
            background: rgba(0, 0, 0, 0.7); /* Nền caption trong suốt */
            border-radius: 10px;
            padding: 10px;
        }
        .carousel-control-prev, .carousel-control-next {
            width: 5%; /* Thu hẹp nút điều hướng */
        }
        .carousel-indicators {
            bottom: 10px;
        }
        .carousel-indicators button {
            background-color: #fff;
            opacity: 0.5;
        }
        .carousel-indicators .active {
            opacity: 1;
            background-color: #3b82f6; /* Đồng bộ màu với navbar */
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

    <!-- Carousel -->
    <div id="aboutCarousel" class="carousel slide mb-5" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#aboutCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#aboutCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#aboutCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://scontent.fhan20-1.fna.fbcdn.net/v/t39.30808-6/493260434_1321150939473651_5749533004573347957_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_ohc=Am4Zgk4DW1EQ7kNvwEhsWNm&_nc_oc=AdkfWJITY2_J1PlFsSHJACkUZ27hzaIPMipWZbT5vEH8mdVM69aw3Ivbbjnrq42In9YamnA6w6B8ekyFOd0t7zBp&_nc_zt=23&_nc_ht=scontent.fhan20-1.fna&_nc_gid=8j3brAFFyYcC23YYEh_mCA&oh=00_AfOg4y2ZyJL4MlL3R2uaIZDW7Zwu9yM5K8jYAWHSXmFG5Q&oe=684E23FB">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Cơ sở vật chất hiện đại</h5>
                    <p>Trang thiết bị tiên tiến, phục vụ tốt nhất cho sinh viên học tập và phát triển</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://scontent.fhan2-3.fna.fbcdn.net/v/t39.30808-6/493226919_1321146849474060_3557209003176295109_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_ohc=ZvWbCIG23pwQ7kNvwF2Txe0&_nc_oc=AdkQPo4-ycooK5Vf4WepcQArdig8GfOjjecn4hF2WVQaWaRFKZ7EEDKLnU88sUyke84Sqicx2vsB5tG6UctTIzPS&_nc_zt=23&_nc_ht=scontent.fhan2-3.fna&_nc_gid=K8Nd1mcxDK60tKpOpR1F0Q&oh=00_AfOmUfuJZGgKAMLXvDjIRAmE6WzKPbUY_Jng40mDFjQ6EQ&oe=684DEFF8">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Đội ngũ giảng viên chuyên môn cao và tâm huyết/h5>
                    <p>Đội ngũ giảng viên tận tâm, giàu kinh nghiệm.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://th.bing.com/th/id/OIP.smo-y7hAbwbYbux5JGTZ2wHaDQ?rs=1&pid=ImgDetMain">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Truyền thống lâu đời</h5>
                    <p>đạt nhiều thành tích cao</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#aboutCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#aboutCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>

<footer>
    © 2025 Hệ thống Quản lý Đề thi. Phát triển bởi Lại Chí Bảo.
</footer>

<!-- Thêm Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>