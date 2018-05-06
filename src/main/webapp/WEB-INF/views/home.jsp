<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-cookies.min.js"></script>
    <script src="https://unpkg.com/@uirouter/angularjs/release/angular-ui-router.min.js"></script>
    <link rel="stylesheet" href="/css/home.css">
    <script src="/js/home-config.js"></script>
    <script src="/js/home-services.js"></script>
    <script src="/js/home-controllers.js"></script>
</head>
<body class="container" ng-app="home">
<nav class="border-bottom fixed-top navbar navbar-expand-lg navbar-expand-md navbar-light bg-white px-5" style="
    box-shadow: 0 0.25rem 0.75rem rgba(0, 0, 0, .05);
" ng-controller="homeCtrl">
    <a class="navbar-brand" href="#">Booking</a>
    <div class="collapse d-flex flex-row navbar-collapse">
        <ul class="navbar-nav">
            <li class="active nav-item px-3">
                <a class="nav-link" ui-sref="home.book">Sách</a>
            </li>
            <li class="nav-item px-3">
                <a class="nav-link" ui-sref="home.category">Thể loại</a>
            </li>
            <li class="nav-item px-3">
                <a class="nav-link" ui-sref="home.author">Tác giả</a>
            </li>
        </ul>
    </div>

    <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
        <sec:authentication var="username" property="principal.username"></sec:authentication>
        <div class="dropdown" ng-mouseenter="toggleDropdown()" ng-mouseleave="toggleDropdown()">

            <button class="border-0 btn btn-outline-dark dropdown-toggle nav-link">
                    ${username}
                <img class="avatar sm rounded-circle ml-2 mr-1" ng-src="{{user.anhDaiDien != null ? user.anhDaiDien : ''}}" ng-alt="{{user.anhDaiDien}}">
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" ng-class="{'show': isOpen}">
                <a class="dropdown-item" href="#">Tài khoản</a>
                <form class="dropdown-item" action="/logout" method="post" class="navbar-nav">
                    <input class="form-control form-control-plaintext nav-link p-0 text-left" type="submit" value="Đăng xuất">
                </form>
            </div>
        </div>
        <div ui-view="cart"></div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <sec:authentication var="user" property="principal"></sec:authentication>
        <a class="border-0 btn btn-outline-secondary nav-link" href="/signup">Đăng ký</a>
        <a class="border-0 btn btn-outline-primary nav-link" href="/login">Đăng nhập</a>
    </sec:authorize>
</nav>
<div class="nav-placeholder"></div>
<div ui-view></div>
</body>
</html>