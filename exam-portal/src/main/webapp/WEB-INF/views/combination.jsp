<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tổ hợp đề thi</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            margin: 0;
            background: #f0f4f8;
            color: #333;
        }

        h2, h3 {
            text-align: center;
            margin: 20px 0;
            color: #1c2c4c;
        }

        .container {
            max-width: 960px;
            margin: auto;
            padding: 20px;
        }

        form {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
            margin-bottom: 40px;
        }

        form label {
            display: block;
            margin-top: 15px;
            font-weight: 600;
            color: #333;
        }

        form input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1.5px solid #ccc;
            border-radius: 8px;
            font-size: 1rem;
            transition: border 0.2s;
        }

        form input:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 4px rgba(0,123,255,0.3);
        }

        .form-buttons {
            display: flex;
            gap: 10px;
            margin-top: 25px;
            flex-wrap: wrap;
        }

        form button {
            padding: 12px 20px;
            font-weight: 600;
            font-size: 1rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .btn-back {
            background-color: #6c757d;
            color: white;
        }

        .btn-back:hover {
            background-color: #545b62;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
        }

        th {
            background: #007bff;
            color: white;
            font-weight: bold;
            padding: 12px;
            border-radius: 8px 8px 0 0;
        }

        td {
            background: #fff;
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
        }

        tr:hover td {
            background-color: #f1f9ff;
        }

        .btn-download {
            background-color: #28a745;
            color: white;
            padding: 8px 14px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            display: inline-block;
            transition: background-color 0.3s ease;
        }

        .btn-download:hover {
            background-color: #218838;
        }

        @media (max-width: 768px) {
            .form-buttons {
                flex-direction: column;
            }

            form button {
                width: 100%;
            }

            table, thead, tbody, th, td, tr {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/layout/header.jsp" />
    <div class="container">
        <h2>Nhập Tổ hợp đề thi</h2>
        <form action="/combination/save" method="post" autocomplete="off">
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

            <div class="form-buttons">
                <button type="submit">Lưu</button>
                <button type="button" class="btn-back" onclick="window.location.href='/dashboard'">Quay lại</button>
            </div>
        </form>

            <!-- Bắt đầu phần tải báo cáo tổ hợp đề thi -->
    <div class="form-section">
        <h3>Tải báo cáo tổ hợp đề thi</h3>
        <form id="downloadForm" autocomplete="off" onsubmit="return redirectDownload(event)" style="display: flex; gap: 10px;">
            <select id="downloadSelect" name="id" required style="flex-grow: 1; padding: 10px; font-size: 1rem; border: 1.5px solid #ccc; border-radius: 8px;">
                <option value="" disabled selected>-- Chọn tổ hợp đề thi --</option>
                <c:forEach var="combo" items="${combinations}">
                    <option value="${combo.id}">
                        ${combo.subjectName} - ${combo.subjectCode} - ${combo.examType}
                    </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-success" style="padding: 12px 20px;">
                <i class="fas fa-download"></i> Tải báo cáo
            </button>
        </form>
    </div>
</div>

<script>
    function redirectDownload(event) {
        event.preventDefault();
        const select = document.getElementById('downloadSelect');
        const id = select.value;
        if (!id) {
            alert('Vui lòng chọn tổ hợp đề thi!');
            return false;
        }
        window.location.href = '${pageContext.request.contextPath}/combination/download/' + encodeURIComponent(id);
        return false;
    }
</script>

        <h3>Danh sách tổ hợp đề thi</h3>
        <table>
            <thead>
                <tr>
                    <th>Môn học</th>
                    <th>Mã môn</th>
                    <th>Loại đề</th>
                    <th>Mã câu hỏi</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="combo" items="${combinations}">
                    <tr>
                        <td>${combo.subjectName}</td>
                        <td>${combo.subjectCode}</td>
                        <td>${combo.examType}</td>
                        <td>${combo.questionCodes}</td>
                        <td>
                            <a href="/combination/download/${combo.id}" class="btn-download">Tải báo cáo</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
