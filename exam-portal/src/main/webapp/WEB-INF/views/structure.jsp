<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Cấu trúc đề thi</title></head>
<body>
<h2>Nhập Cấu trúc đề thi</h2>
<form action="/structure/save" method="post">
    Môn học: <input name="subjectName" /><br/>
    Mã môn: <input name="subjectCode" /><br/>
    Loại đề: <input name="examType" /><br/>
    Kỳ: <input name="semester" value="3" /><br/>
    Năm học: <input name="academicYear" value="2023-2024" /><br/>
    Mức độ: <input name="difficultyLevel" /><br/>
    Mô tả: <input name="structureDescription" /><br/>
    <button type="submit">Lưu</button>
</form>

<h3>Danh sách cấu trúc</h3>
<table border="1">
    <tr><th>Môn học</th><th>Mô tả</th></tr>
    <c:forEach var="s" items="${structures}">
        <tr>
            <td>${s.subjectName}</td>
            <td>${s.structureDescription}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
