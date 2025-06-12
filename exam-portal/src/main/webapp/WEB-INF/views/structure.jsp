<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Nhập Cấu trúc đề thi</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h3 class="card-title mb-4">
                <c:choose>
                    <c:when test="${structureForm.id != null}">
                        Cập nhật Cấu trúc đề thi
                    </c:when>
                    <c:otherwise>
                        Nhập Cấu trúc đề thi
                    </c:otherwise>
                </c:choose>
            </h3>

            <form action="/structure/save" method="post">
                <input type="hidden" name="id" value="${structureForm.id}" />

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Môn học</label>
                        <input type="text" name="subjectName" class="form-control" value="${structureForm.subjectName}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Mã môn</label>
                        <input type="text" name="subjectCode" class="form-control" value="${structureForm.subjectCode}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Loại đề</label>
                        <input type="text" name="examType" class="form-control" value="${structureForm.examType}" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Kỳ</label>
                        <input type="text" name="semester" class="form-control" value="${structureForm.semester}" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Năm học</label>
                        <input type="text" name="academicYear" class="form-control" value="${structureForm.academicYear}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Số tín chỉ</label>
                        <input type="number" name="credit" class="form-control" value="${structureForm.credit}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Thời gian thi (phút)</label>
                        <input type="number" name="duration" class="form-control" value="${structureForm.duration}" required>
                    </div>
                </div>


                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Mức độ</label>
                        <input type="text" name="difficultyLevel" class="form-control" value="${structureForm.difficultyLevel}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Mô tả</label>
                        <input type="text" name="structureDescription" class="form-control" value="${structureForm.structureDescription}" required>
                    </div>
                </div>



                <button type="submit" class="btn btn-success">
                    <i class="fa fa-save"></i>
                    <c:choose>
                        <c:when test="${structureForm.id != null}"> Cập nhật </c:when>
                        <c:otherwise> Lưu </c:otherwise>
                    </c:choose>
                </button>
            </form>
        </div>
    </div>

    <div class="mt-5">
        <h4>Danh sách Cấu trúc đề thi</h4>
        <table class="table table-bordered table-hover bg-white mt-3 shadow-sm">
            <thead class="thead-dark">
                <tr>
                    <th>Môn học</th>
                    <th>Mã môn</th>
                    <th>Số tín chỉ</th>
                    <th>Loại đề</th>
                    <th>Thời gian thi</th>
                    <th>Kỳ</th>
                    <th>Năm học</th>
                    <th>Mức độ</th>
                    <th>Mô tả</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${structures}">
                    <tr>
                        <td>${s.subjectName}</td>
                        <td>${s.subjectCode}</td>
                        <td>${s.credit}</td>
                        <td>${s.examType}</td>
                        <td>${s.duration}</td>
                        <td>${s.semester}</td>
                        <td>${s.academicYear}</td>
                        <td>${s.difficultyLevel}</td>
                        <td>${s.structureDescription}</td>
                        <td>
                            <a href="/structure/edit/${s.id}" class="btn btn-sm btn-warning">Sửa</a>
                            <a href="/structure/delete/${s.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa cấu trúc này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/dashboard" class="btn btn-primary mt-3">← Quay lại trang chủ</a>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
