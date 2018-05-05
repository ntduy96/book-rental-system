<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sign Up</title>
    <%--<link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/form.css">
</head>
<body class="text-center">
<form:form modelAttribute="user" action="/signup" method="post" acceptCharset="utf8" cssClass="form needs-validation" novalidate="true">
    <h2>Registration Form</h2>

    <form:errors element="div" class="alert alert-warning" role="alert"/>
    <div class="invalid-feedback" id="passwordMatchingErrors"></div>

    <div class="form-group">
        <form:label path="tenNguoiDung" cssClass="sr-only">Tên người dùng</form:label>
        <form:input path="tenNguoiDung" required="true" placeholder="Tên người dùng" cssClass="form-control"/>
        <form:errors path="tenNguoiDung" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="tenNguoiDungErrors"></div>
        <form:label path="email" cssClass="sr-only">Email</form:label>
        <form:input path="email" required="true" placeholder="Email" type="email" cssClass="form-control"/>
        <form:errors path="email" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="emailErrors"></div>
    </div>
    <div class="form-group">
        <form:label path="hoTenNguoiDung" cssClass="sr-only">Họ và tên</form:label>
        <form:input path="hoTenNguoiDung" required="true" placeholder="Họ và tên" cssClass="form-control"/>
        <form:errors path="hoTenNguoiDung" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="hoTenNguoiDungErrors"></div>
        <form:label path="soCmnd" cssClass="sr-only">Số CMND</form:label>
        <form:input path="soCmnd" required="true" placeholder="Số CMND" cssClass="form-control"/>
        <form:errors path="soCmnd" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="soCmndErrors"></div>
        <form:label path="diaChiNguoiDung" cssClass="sr-only">Địa chỉ</form:label>
        <form:input path="diaChiNguoiDung" required="true" placeholder="Địa chỉ" cssClass="form-control"/>
        <form:errors path="diaChiNguoiDung" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="diaChiNguoiDungErrors"></div>
    </div>
    <div class="form-group">
        <form:label path="password" cssClass="sr-only">Mật khẩu</form:label>
        <form:password path="password" required="true" placeholder="Mật khẩu" cssClass="form-control"/>
        <form:errors path="password" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="passwordErrors"></div>
        <form:label path="confirmPassword" cssClass="sr-only">Xác nhận mật khẩu</form:label>
        <form:password path="confirmPassword" required="true" placeholder="Xác nhận mật khẩu" cssClass="form-control"/>
        <form:errors path="confirmPassword" element="div" cssClass="invalid-feedback"/>
        <div class="invalid-feedback" id="confirmPasswordErrors"></div>
    </div>
    <button type="submit" class="btn btn-lg btn-primary btn-block">Đăng ký</button>
    <small class="form-text text-muted"><label>Bạn đã có tài khoản rồi hãy</label></small>
    <a href="/login" class="btn btn-lg btn-outline-primary btn-block">Đăng nhập</a>
</form:form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="/js/signup-validate.js"></script>

</body>
</html>
