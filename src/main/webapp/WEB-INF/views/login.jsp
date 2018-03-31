<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<h2>Login</h2>
<c:if test="${param.error != null}">
    <div>Invalid username and password.</div>
</c:if>
<c:if test="${param.logout != null}">
    <div>You have been logged out.</div>
</c:if>
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
