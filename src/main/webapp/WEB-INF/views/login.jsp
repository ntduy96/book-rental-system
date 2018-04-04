<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Log In</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/form.css">
</head>
<body class="text-center">
<form action="/login" method="post" class="form">
    <h1 class="h3 mb-3 font-weight-normal">Please log in</h1>
    <c:if test="${param.error != null}">
        <div class="alert alert-warning" role="alert">Invalid username and password.</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-info" role="alert">You have been logged out.</div>
    </c:if>
    <c:if test="${param.exist.equals('true')}">
        <div class="alert alert-warning" role="alert">${param.message}. Please log in!</div>
    </c:if>
    <c:if test="${param.success.equals('true')}">
        <div class="alert alert-success" role="alert">You registered successfully. Please log in!</div>
    </c:if>
    <label for="inputUsername" class="sr-only">Username</label>
    <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    <small class="form-text text-muted"><label>You haven't had an account yet</label></small>
    <a href="/signup" class="btn btn-lg btn-outline-primary btn-block">Sign up</a>
</form>
</body>
</html>
