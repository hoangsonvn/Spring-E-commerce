<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="resource/common/authen.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body style="background-image: url(resource/client/images/bg.jpg);">
<!-- Authen form -->
<form action="<c:url value="/email"/>" method="post">
    <div class="auth-form" style="background-color: white;">
        <div class="auth-form-container">
            <a href="<c:url value="/client/home"/>"><h5 class="auth-form-title"
                                      style="text-align: center; padding-top: 20px;">
                PiFood</h5></a>

            <c:if test="${wrong != null}">
                <div style="display: flex; margin-bottom: 2px; color: red;">
                    <i style="font-size:18px" class="fa">&#xf071;</i>
                    <span style="margin-left: 2px;">${wrong}</span>
                    <br>
                    <br>
                    <br>
                </div>
            </c:if>
            <hr style="margin-top: -35px;" />

            <div class="auth-form-form"
                 style="margin-top: 15px; margin-bottom: -13px;">
                <div class="auth-form-group">

                    <input class="auth-form-input" type="text" name="email"
                           placeholder="Enter email" required="required" />
                </div>
            </div>

            <div class="auth-form-controls"
                 style="margin-left: 80px; margin-top: 15px;">
                <button type="submit" class="btn" style="cursor: pointer;">Confirm</button>
            </div>
            <div style="padding-bottom: 20px;"></div>
        </div>
    </div>
</form>
</body>
</html>
