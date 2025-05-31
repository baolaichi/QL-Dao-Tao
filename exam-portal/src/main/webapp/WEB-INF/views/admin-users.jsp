<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>Quản lý người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Quản lý người dùng</h2>

    <!-- THÊM USER -->
    <form class="row g-3 mb-4" action="/admin/users/add" method="post">
        <div class="col-md-3">
            <input name="username" class="form-control" placeholder="Tài khoản" required />
        </div>
        <div class="col-md-3">
            <input name="password" type="password" class="form-control" placeholder="Mật khẩu" required />
        </div>
        <div class="col-md-3">
            <select name="role" class="form-select">
                <option value="GIANGVIEN">Giảng viên</option>
                <option value="THANHTRA">Thanh tra</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-primary w-100">➕ Thêm</button>
        </div>
    </form>

    <!-- TÌM KIẾM USER -->
    <form class="row g-2 mb-4" method="get" action="/admin/users">
        <div class="col-md-10">
            <input name="keyword" class="form-control" placeholder="Tìm theo tài khoản..." value="${keyword}" />
        </div>
        <div class="col-md-2">
            <button class="btn btn-secondary w-100" type="submit">🔍 Tìm</button>
        </div>
    </form>

    <!-- THÔNG BÁO -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- DANH SÁCH USER -->
    <table class="table table-bordered bg-white">
        <thead class="table-dark">
        <tr>
            <th>Tài khoản</th>
            <th>Vai trò</th>
            <th style="width:100px;">Xoá</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.role}</td>
                <td>
                    <form action="/admin/users/delete" method="post" onsubmit="return confirm('Xoá người dùng này?');">
                        <input type="hidden" name="username" value="${user.username}" />
                        <button class="btn btn-sm btn-danger">Xoá</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-outline-secondary mt-3" href="/dashboard">⬅ Quay lại</a>
</div>

</body>
</html>
