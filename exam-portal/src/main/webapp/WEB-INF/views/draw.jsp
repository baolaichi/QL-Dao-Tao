<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Biên bản bốc thăm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        h2, h3 {
            color: #333;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            width: 450px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        form input[type="text"],
        form input[type="date"],
        form input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 6px 0 16px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form button {
            background-color: #28a745;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        form button:hover {
            background-color: #218838;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #ffffff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        table th, table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #007bff;
            color: white;
        }

        .back-btn {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 14px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
        }

        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>Nhập Biên bản bốc thăm</h2>
<form action="/draw/save" method="post">
    Môn học: <input name="subjectName" type="text" required />
    Mã môn: <input name="subjectCode" type="text" required />
    Kỳ: <input name="semester" type="text" value="3" required />
    Năm học: <input name="academicYear" type="text" value="2023-2024" required />
    Ngày thi: <input name="examDate" type="date" required />
    Ca thi: <input name="examShift" type="text" required />
    Số đề: <input name="numberOfQuestions" type="number" required />
    Ghi chú: <input name="note" type="text" />
    <button type="submit">Lưu</button>
</form>

<h3>Danh sách Biên bản bốc thăm</h3>
<table>
    <tr>
        <th>Môn học</th>
        <th>Ngày thi</th>
        <th>Số đề</th>
    </tr>
    <c:forEach var="d" items="${drawReports}">
        <tr>
            <td>${d.subjectName}</td>
            <td>${d.examDate}</td>
            <td>${d.numberOfQuestions}</td>
        </tr>
    </c:forEach>
</table>

<a href="/dashboard" class="back-btn">← Quay lại trang chủ</a>

</body>
</html>
