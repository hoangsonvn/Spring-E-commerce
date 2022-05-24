<%@ page import="com.demo6.shop.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>

<body>
<div th:fragment="header">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <img src="<c:url value="/resource/admin/assets/img/logo-dark.png"/>" alt="Klorofil Logo"
                 class="img-responsive logo">
        </div>
        <div class="container-fluid">
            <div id="navbar-menu">
                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <input type="text" value="" class="form-control"
                               placeholder="Search dashboard..."> <span
                            class="input-group-btn"><button type="button"
                                                            class="btn btn-primary">Go</button></span>
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<c:url value="/admin/home"/>"> <i class="fa fa-home"
                                                                   style="font-size: 18px;"></i><span> Home</span>
                    </a></li>

                    <sec:authorize access="isAuthenticated()">
                        <ul class="usermenu" style="display: flex;">
                            <li style="list-style:none; margin-top: 10px;margin-right: 15px;">
                                <img class="img-circle" src="../download?image=<%=SecurityUtils.getPrincipal().getAvatar()%>"
                                     style="width: 26px;height:26px; margin-top: -4px;">
                                <span
                                        style="margin-left:10px;margin-right:15px;color: #001c4a; font-size: 14px;font-weight: bold; "><%=SecurityUtils.getPrincipal().getFullname()%></span>
                            </li>
                            <li style="list-style: none;margin-top: 6px;"><a href="<c:url value="/logout"/>"> <i class="material-icons"
                                                        style="color: red;">&#xe8ac;</i></a>
                        </ul>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</div>
</body>
</html>