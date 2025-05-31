<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đăng ký tài khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f4f8;
            padding: 40px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .register-container {
            max-width: 400px;
            margin: auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        button:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
        }

        .login-link {
            text-align: center;
            margin-top: 20px;
        }

        .login-link a {
            color: #007bff;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Đăng ký người dùng</h2>

    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <form action="/register" method="post">
        <label>Tài khoản:</label>
        <input name="username" required />

        <label>Mật khẩu:</label>
        <input type="password" name="password" required />

        <label>Nhập lại mật khẩu:</label>
        <input type="password" name="confirmPassword" required />

        <label>Vai trò:</label>
        <select name="role" required>
            <option value="GIANGVIEN">Giảng viên</option>
            <option value="THANHTRA">Thanh tra</option>
            <option value="ADMIN">Quản trị viên</option>
        </select>

        <button type="submit">Đăng ký</button>
    </form>

    <div class="login-link">
        <p>Đã có tài khoản? <a href="/">Đăng nhập</a></p>
    </div>
</div>

</body>
</html>
