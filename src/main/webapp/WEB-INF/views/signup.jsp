<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h1>Registration Form</h1>
<form:form modelAttribute="user" action="/signup" method="post" acceptCharset="utf8">
    <div>
        <label>Tên người dùng</label>
        <form:input path="tenNguoiDung"/>
        <form:errors path="tenNguoiDung" element="div"/>
    </div>
    <div>
        <label>Họ và tên</label>
        <form:input path="hoTenNguoiDung"/>
        <form:errors path="hoTenNguoiDung" element="div"/>
    </div>
    <div>
        <label>Số CMND</label>
        <form:input path="soCmnd"/>
        <form:errors path="soCmnd" element="div"/>
    </div>
    <div>
        <label>Địa chỉ</label>
        <form:input path="diaChiNguoiDung"/>
        <form:errors path="diaChiNguoiDung" element="div"/>
    </div>
    <div>
        <label>email</label>
        <form:input path="email" type="email"/>
        <form:errors path="email" element="div"/>
    </div>
    <div>
        <label>password</label>
        <form:password path="password"/>
        <form:errors path="password" element="div"/>
    </div>
    <div>
        <label>Xác nhận mật khẩu</label>
        <form:input path="confirmPassword"/>
        <form:errors path="confirmPassword" element="div"/>
    </div>

    <form:errors element="div"/>

    <button type="submit">Đăng ký</button>
</form:form>

<div>
    Bạn đã có tài khoản rồi hãy <a href="/login">Đăng nhập</a>
</div>

</body>
</html>
