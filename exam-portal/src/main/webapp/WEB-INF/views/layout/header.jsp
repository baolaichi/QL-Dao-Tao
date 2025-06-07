<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
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
            <a href="${pageContext.request.contextPath}/report/pdf">Tải PDF</a>
            <a href="/report/excel" class="btn btn-success">📊 Xuất Excel Báo cáo</a>
            <a href="/admin/users/export" class="btn btn-success">📥 Xuất Excel User</a>
        </c:if>

        <c:if test="${sessionScope.role == 'ADMIN'}">
             <a href="/admin/users">Quản lý người dùng</a>
        </c:if>

        <a href="/logout">Đăng xuất</a>
    </div>
</div>