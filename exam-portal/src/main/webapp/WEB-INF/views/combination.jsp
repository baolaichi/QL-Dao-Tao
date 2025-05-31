<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tổ hợp đề thi</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            padding: 30px;
            margin: 0;
        }

        h2, h3 {
            color: #333;
            text-align: center;
        }

        form {
            background-color: white;
            padding: 20px 30px;
            max-width: 600px;
            margin: 0 auto 30px auto;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        form label {
            display: block;
            margin: 12px 0 6px;
            font-weight: bold;
        }

        form input {
            width: 100%;
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        form button {
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        form button:hover {
            background-color: #0056b3;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f1f1f1;
        }

        th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>

<h2>Nhập Tổ hợp đề thi</h2>
<form action="/combination/save" method="post">
    <label for="subjectName">Môn học</label>
    <input name="subjectName" id="subjectName" required />

    <label for="subjectCode">Mã môn</label>
    <input name="subjectCode" id="subjectCode" required />

    <label for="examType">Loại đề</label>
    <input name="examType" id="examType" required />

    <label for="semester">Kỳ</label>
    <input name="semester" id="semester" value="3" required />

    <label for="academicYear">Năm học</label>
    <input name="academicYear" id="academicYear" value="2023-2024" required />

    <label for="questionCodes">Mã các câu hỏi (phân cách bởi dấu phẩy)</label>
    <input name="questionCodes" id="questionCodes" required />

    <label for="note">Ghi chú</label>
    <input name="note" id="note" />

    <button type="submit">Lưu</button>
    <a href="/dashboard" style="text-decoration: none;">
    <button type="button">Quay lại</button>
</a>

</form>

<h3>Danh sách tổ hợp</h3>
<table>
    <tr>
        <th>Môn học</th>
        <th>Mã câu hỏi</th>
    </tr>
    <c:forEach var="c" items="${combinations}">
        <tr>
            <td>${c.subjectName}</td>
            <td>${c.questionCodes}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
