<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Đăng nhập hệ thống</title>
    <style>
        body {
            background: linear-gradient(to right, #4facfe, #00f2fe);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .logo {
            width: 80px;
            height: 80px;
            margin-bottom: 15px;
        }

        h2 {
            margin-bottom: 25px;
            color: #333;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-bottom: 15px;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
        }

        .remember-me {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .remember-me input {
            margin-right: 8px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <img src="logo.png" alt="Logo" class="logo" />
        <h2>Đăng nhập</h2>

        <!-- Thông báo lỗi -->
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="/login" method="post">
            <label for="username">Tài khoản:</label>
            <input type="text" name="username" id="username" required />

            <label for="password">Mật khẩu:</label>
            <input type="password" name="password" id="password" required />

            <div class="remember-me">
                <input type="checkbox" id="remember" name="remember" />
                <label for="remember" style="font-weight: normal;">Ghi nhớ đăng nhập</label>
            </div>

            <button type="submit">Đăng nhập</button>
            <p style="margin-top: 15px;">
                <a href="/register" style="color: #007bff;">Chưa có tài khoản? Đăng ký</a>
            </p>

        </form>
    </div>
</body>
</html>
