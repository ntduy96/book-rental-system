<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Home ${user}</h1>
<c:if test="${user != null}">
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
    </form>
</c:if>
<c:if test="${user == null}">
    <form action="/login" method="get">
        <input type="submit" value="Log In"/>
    </form>
</c:if>

</body>
</html>
