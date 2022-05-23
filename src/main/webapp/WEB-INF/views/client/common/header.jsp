<%@ page import="com.demo6.shop.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/favicon.png">
<title>Welcome to PiFood</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js">

	</script><script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script><![endif]-->
</head>
<body id="home">

<style>

	.red-color {
		color:red;
	}
</style>
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-sm-2">
					<div class="logo">
						<a href="<c:url value="/client/home"/>"><img
							src="../resource/client/images/logo.png" alt="PiFood"></a>
					</div>
				</div>
				<div class="col-md-10 col-sm-10">
					<div class="header_top">
						<div class="row">
							<div class="col-md-3">
								<ul class="option_nav">
									<li class="dorpdown"><a href="#">Eng</a></li>
									<li class="dorpdown"><a href="#">USD</a></li>
								</ul>
							</div>
							<div class="col-md-6">
								<ul class="topmenu">
									<li><a href="#">News</a></li>
									<li><a href="#">Service</a></li>
									<li><a href="#">Recruiment</a></li>
									<li><a href="#">Media</a></li>
									<li><a href="#">Support</a></li>
								</ul>
							</div>
							<div class="col-md-3">
								<c:if test="${sessionScope.user == null}">
									<sec:authorize access="!isAuthenticated()">
									<ul class="usermenu">
										<li><a href="<c:url value="/login"/>" class="log">Login</a></li>
										<li><a href="<c:url value="/register"/>" class="reg">Register</a></li>
									</ul>
									</sec:authorize>
									 <sec:authorize access="isAuthenticated()">
										<ul class="usermenu" style="display: flex;">
											<li> <a href="<c:url value="/client/profile"/>"><img class="img-circle"
																								 src="../download?image=<%=SecurityUtils.getPrincipal().getAvatar()%>"
																								 style="width: 26px;height:26px; margin-top: -4px;">
											</a><a href="<c:url value="/client/profile"/>" style="margin-left: -22px;"><span style="margin-left: 5px; color: white; font-size: 14px;"><%=SecurityUtils.getPrincipal().getFullname()%></span></a></li>
											<li><i
													style="font-size: 22px; margin-top: -2px; color: #F7544A;"
													class="fa">&#xf011;</i> <a
													style="position: absolute; margin-left: -18px;"
													href="<c:url value="/logout"/>">Logout</a></li>
										</ul>
									 </sec:authorize>

								</c:if>

								<c:if test="${sessionScope.user != null}">
									<ul class="usermenu" style="display: flex;">
										<li> <a href="<c:url value="/client/profile"/>"><img class="img-circle"
											src="<c:url value="/download?image=${sessionScope.user.avatar}"/>"
											style="width: 26px;height:26px; margin-top: -4px;">
											</a><a href="<c:url value="/client/profile"/>" style="margin-left: -22px;"><span style="margin-left: 5px; color: white; font-size: 14px;">${sessionScope.user.fullname}</span></a></li>
										<li><i
											style="font-size: 22px; margin-top: -2px; color: #F7544A;"
											class="fa">&#xf011;</i> <a
											style="position: absolute; margin-left: -18px;"
											href="<c:url value="/logout"/>">Logout</a></li>
									</ul>
								</c:if>

							</div>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="header_bottom">
						<ul class="option">
							<li id="search" class="search">
								<form action="<c:url value="/client/search"/>"  method="get">
									<input class="search-submit" type="submit" value=""><input
										class="search-input" placeholder="Enter your search term..."
										type="text" value="" name="text">
								</form>
							</li>


							<li class="option-cart"><a href="<c:url value="/client/listcart"/>"
													   class="cart-icon">cart <span class="cart_no">${TotalQuantyCart}</span>
							</a></li>

							<li class="option-cart"><a href="<c:url value="/client/listlike"/> " class="btn btn-danger btn-sm"
							> <span class="glyphicon glyphicon-heart">${size}</span>
							</a></li>

						</ul>
						<div class="navbar-header">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span><span
									class="icon-bar"></span><span class="icon-bar"></span><span
									class="icon-bar"></span>
							</button>
						</div>
						<div class="navbar-collapse">
							<ul class="nav">
								<li><a style="text-transform: none;" href="<c:url value="/client/home"/>">Home</a></li>
								<c:forEach items="${categories}" var="category">
									<li><a style="text-transform: none;"
										href="<c:url value="/client/search?categoryId=${category.categoryId}"/>">${category.categoryName}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>