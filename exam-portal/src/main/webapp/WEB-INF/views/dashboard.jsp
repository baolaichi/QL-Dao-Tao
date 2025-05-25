<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Trang chính</title></head>
<body>
<h2>Xin chào, ${sessionScope.username} [${sessionScope.role}]</h2>

<ul>
    <li><a href="/structure">Quản lý Cấu trúc đề thi</a></li>
    <li><a href="/combination">Quản lý Tổ hợp đề thi</a></li>
    <li><a href="/draw">Quản lý Biên bản bốc thăm</a></li>
    <li><a href="/report/pdf">Tải báo cáo PDF</a></li>
    <li><a href="/logout">Đăng xuất</a></li>
</ul>

</body>
</html>
