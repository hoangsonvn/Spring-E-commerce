<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
</head>
<body>
<!-- LEFT SIDEBAR -->
<div th:fragment="category">
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll" style="padding-top: 20px;">
            <nav>
                <ul class="nav">
                    <li><a href="<c:url value="/admin/home"/>" class="><i class="lnr lnr-home"></i> <span>Home</span></a></li>
                    <li><a href="<c:url value="/admin/user-list"/>" class=""><i class="lnr lnr-users"></i> <span>User</span></a></li>
                    <li><a href="<c:url value="/admin/product-list"/>" class=""><i class="lnr lnr-gift"></i> <span>Product</span></a></li>
                    <li><a href="<c:url value="/admin/order-list"/>" class=""><i class="lnr lnr lnr-dice"></i>
                        <span>Order</span></a></li>
                    <li><a href="<c:url value="/admin/listCategory"/>" class=""><i class="lnr lnr lnr-dice"></i>
                        <span>Category</span></a></li>
                    <li><a href="<c:url value="/admin/canvasjschart"/> " class=""><i class="lnr lnr lnr-dice"></i>
                        <span>Canvasjschart</span></a></li>
                    <li><a href="<c:url value="/admin/statistical"/> " class=""><i class="lnr lnr lnr-dice"></i>
                        <span>Statistical</span></a></li>
                    <li><a href="<c:url value="/client/home"/> " class=""><i class="lnr lnr lnr-dice"></i>
                        <span>Go to Shop</span></a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- END LEFT SIDEBAR -->
</body>
</html>