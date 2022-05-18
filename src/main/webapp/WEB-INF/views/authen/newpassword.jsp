<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="resource/common/authen.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body style="background-image: url(resource/client/images/bg.jpg);">
<!-- Authen form -->
<form action="<c:url value="/newpasswordget"/>" method="post">
    <div class="auth-form" style="background-color: white;">
        <div class="auth-form-container">
            <div class="auth-form-header">
                <h3 class="auth-form-title">Authen</h3>
                <a href="register" class="auth-form-subtitle">Register</a>
            </div>
            <p style="color: red; margin-top: -15px; margin-left: 3x; margin-bottom: 5px;">${errPassword}</p>
            <div class="auth-form-form" style="margin-top: 15px;">
                <div class="auth-form-group">
                    <input class="auth-form-input" type="password" name="password"
                           placeholder="Password" required="required" />
                </div>
                <div class="auth-form-group">
                    <input class="auth-form-input" type="password" name="repassword"
                           placeholder="Password" required="required" />
                </div>

            </div>

            <div class="auth-form-controls">
                <a href="<c:url value="/client/home"/>" class="btn">BACK</a>
                <button type="submit" class="btn" style="cursor: pointer;">CONFIRM</button>
            </div>
        </div>

    </div>
</form>
</body>
</html>