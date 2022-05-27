<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard </title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/linearicons/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/chartist/css/chartist-custom.css'/>">
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/main.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/demo.css'/>">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">

<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value='../resource/admin/assets/img/apple-icon.png'/>">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='../resource/admin/assets/img/favicon.png'/>">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<%@include file="../common/header.jsp"%>
		<jsp:include page="../common/category.jsp" />
		<!-- MAIN -->
				<!-- MAIN CONTENT -->
		<form action="<c:url value="/admin/product-delete"/>" method="post">
			<div class="main">
				<div class="main-content">
					<div class="container-fluid">
						<!-- OVERVIEW -->
						<c:if test="${messagecreate != null}">
							<div class="alert alert-success">
									${messagecreate}
							</div></c:if>
						<c:if test="${messagedelete != null}">
							<div class="alert alert-success">
									${messagedelete}
							</div></c:if>
						<c:if test="${messageupdate != null}">
							<div class="alert alert-success">
									${messageupdate}
							</div></c:if>
						<c:if test="${tick != null }">
							<div class="alert alert-warning">${tick}
							</div>
						</c:if>
						<div class="panel panel-headline">
							<div class="panel-body">
								<form action="<c:url value="/admin/product-delete"/>" method="post">
									<div class="row">
										<div class="col-md-12">
											<div class="panel">
												<div class="panel-heading">
													<h3 class="panel-title">PRODUCT LIST</h3>
													<p class="demo-button">
														<a class="btn btn-default" href="product-list"
															style="padding: 4px 8px; text-decoration: none; border: none; box-shadow: 2px 2px gray; font-weight: bold;">All Product</a>


														<c:forEach items="${categories}" var="item">
															<a class="btn btn-danger" href="<c:url value="/admin/product-list-by-category?categoryId=${item.categoryId}"/>"
															   style="background-color: gray; padding: 4px 8px; text-decoration: none; border: none; margin-left: 5px;">
																${item.categoryName}</a>
														</c:forEach>
													</p>
													<div class="right">
														<button type="submit">
															<span class="label label-danger"
																style="font-size: 15px; margin-right: 15px;">Delete</span>
														</button>
														<a href="<c:url value="/admin/product-create"/>"><span
															class="label label-success" style="font-size: 15px;">Create
																new product</span></a>
													</div>
												</div>
												<div class="panel-body no-padding">
													<table class="table" style="margin: auto;">
														<thead>
															<tr>
																<th><input type="checkbox" name="all" id="checkAll" style="cursor: pointer;"/></th>
																<th>ID</th>
																<th>Product Name</th>
																<th>Price</th>
																<th>Quantity</th>
																<th>Sale</th>
																<th style="width: 10%;">Image</th>
																<th>Edit</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${products}" varStatus="loop"
																var="product">
																<tr>
																	<td style="vertical-align: middle;"><input
																		class="checkbox" type="checkbox" name="productId"
																		value="${product.productId}" id="${loop.count}" style="cursor: pointer;"/></td>
																	<td style="vertical-align: middle;">${product.productId}</td>
																	<td style="vertical-align: middle;">${product.productName}</td>
																	<td style="vertical-align: middle;"><span
																		style="color: #41B314; font-weight: bold;">$${product.price - (product.price * (product.saleDTO.salePercent / 100))}0</span><br />
																		<c:if test="${product.saleDTO.salePercent != 0}">
																		<span style="text-decoration: line-through;'">$${product.price}.0</span>
																		</c:if>
																	</td>
																	<td style="vertical-align: middle;">${product.quantity}</td>
																	<td style="vertical-align: middle; color: #D9534F; font-weight: bold;">-${product.saleDTO.salePercent}%</td>
																	<td style="vertical-align: middle;"><img
																		style="width: 70%;" src=" <c:url value="/download?image=${product.image}"/>"></td>

																	<td style="vertical-align: middle;"><img
																			style="width: 70%;" src="https://bucketslhs.s3.amazonaws.com/${product.image}"></td>
																	<td style="vertical-align: middle;"><a href="<c:url value="/admin/product-update?productId=${product.productId}"/>">
																			<span class="label label-warning"
																			style="font-size: 15px;">Update</span>
																	</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<c:if test="${totalPage != 0}">
											<nav aria-label="Page navigation example"
												style="margin-top: -30px;">
												<ul class="pagination">
													<c:forEach begin="0" end="${totalPage-1}" var="i">
													<li class="page-item">
													<a class="page-link" href="<c:url value="/admin/product-list?pageIndex=${i}"/>">
														<c:if test="${pageIndex == i}">
														</c:if>
														${i+1}</a></li>
													</c:forEach>
												</ul>
											</nav></c:if>
										</div>
										<div id="headline-chart" class="ct-chart"></div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- END OVERVIEW -->
				</div>
				<!-- END MAIN CONTENT -->
			</div>
		</form>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">
					&copy; 2017 <a href="https://www.themeineed.com" target="_blank">Theme
						I Need</a>. All Rights Reserved.
				</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script>
            let userSelection = document.getElementsByClassName('checkbox');
            let checkAll = document.getElementById('checkAll')


            checkAll.addEventListener('click', () => {
                if (checkAll.checked == true) {
                    for (let i = 1; i <= userSelection.length; i++) {
                        document.getElementById(i).checked = true
                    }
                } else {
                    for (let i = 1; i <= userSelection.length; i++) {
                        document.getElementById(i).checked = false
                    }
                }

            })
   </script>
</body>
</body>
</html>