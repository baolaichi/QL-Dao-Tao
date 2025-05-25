<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Tổ hợp đề thi</title></head>
<body>
<h2>Nhập Tổ hợp đề thi</h2>
<form action="/combination/save" method="post">
    Môn học: <input name="subjectName" /><br/>
    Mã môn: <input name="subjectCode" /><br/>
    Loại đề: <input name="examType" /><br/>
    Kỳ: <input name="semester" value="3" /><br/>
    Năm học: <input name="academicYear" value="2023-2024" /><br/>
    Mã các câu hỏi (phân cách bởi dấu phẩy): <input name="questionCodes" /><br/>
    Ghi chú: <input name="note" /><br/>
    <button type="submit">Lưu</button>
</form>

<h3>Danh sách tổ hợp</h3>
<table border="1">
    <tr><th>Môn học</th><th>Mã câu hỏi</th></tr>
    <c:forEach var="c" items="${combinations}">
        <tr>
            <td>${c.subjectName}</td>
            <td>${c.questionCodes}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
