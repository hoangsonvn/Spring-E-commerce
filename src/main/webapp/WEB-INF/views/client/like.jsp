<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../resource/client/images/favicon.png">
    <link href="../resource/client/css/bootstrap.css" rel="stylesheet">
    <link
            href='http://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,700,500italic,100italic,100'
            rel='stylesheet' type='text/css'>
    <link href="../resource/client/css/font-awesome.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../resource/client/css/flexslider.css"
          type="text/css" media="screen" />
    <link href="../resource/client/css/sequence-looptheme.css"
          rel="stylesheet" media="all" />
    <link href="../resource/client/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script><script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script><![endif]-->
</head>
<body id="home">
<div class="wrapper">
    <jsp:include page="common/header.jsp" />

    <div class="container_fullwidth">
        <div class="container">


            <div class="clearfix"></div>
            <div class="container_fullwidth">
                <div class="container shopping-cart">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="title">Shopping Cart</h3>
                            <div class="clearfix"></div>
                            <table class="shop-table">
                                <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Details</th>
                                    <th>Price</th>
                                    <th>Delete</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${likeMap}">
                                    <tr>
                                        <td><a href="<c:url value="/client/product-details?productId=${item.value.productId}"/>"><img
                                                src="<c:url value="/download?image=${item.value.image}"/>">
                                                alt=""></a></td>
                                        <td>
                                            <div class="shop-details">
                                                <div class="productname">${item.value.productName}</div>
                                                <p>
                                                    <img alt="" src="../resource/client/images/star.png">
                                                    <a class="review_num" href="#"> 02 Review(s) </a>
                                                </p>

                                                <p>
                                                    Sale : <strong class="pcode">-${item.value.saleDTO.salePercent}%</strong>
                                                </p>
                                                <p>
                                                    Product Code : <strong class="pcode">${item.value.productId}</strong>
                                                </p>
                                            </div>
                                        </td>
                                        <td>
                                            <h5 style="color: #41B314; font-weight: bold;">$${Math.round(item.value.price-item.value.saleDTO.salePercent*item.value.price/100)}.00</h5>
                                            <c:if
                                                    test="${item.value.saleDTO.salePercent > 0}">
                                                <p style="font-size: 16px; padding-top: 7px; text-decoration: line-through;">$${item.value.price}.00</p>
                                            </c:if>
                                        </td>


                                        <td><a
                                                href="<c:url value="/client/deletelike/${item.value.productId}"/>">
                                            <i class="material-icons">&#xe92b;</i>
                                        </a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>


                                <tfoot>
                                <tr>
                                    <td colspan="6"><a href="<c:url value="/client/home"/>"><button
                                            class="pull-left">Continue Shopping</button></a>
                                </tr>
                                </tfoot>
                            </table>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 			<div th:replace="client/common/ourbands :: ourbands"></div> -->

        </div>
    </div>

    <jsp:include page="common/footer.jsp" />
</div>
<!-- Bootstrap core JavaScript==================================================-->
<script type="text/javascript"
        src="../resource/client/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
        src="../resource/client/js/jquery.easing.1.3.js"></script>
<script type="text/javascript"
        src="../resource/client/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="../resource/client/js/jquery.sequence-min.js"></script>
<script type="text/javascript"
        src="../resource/client/js/jquery.carouFredSel-6.2.1-packed.js"></script>
<script defer src="../resource/client/js/jquery.flexslider.js"></script>
<script type="text/javascript"
        src="../resource/client/js/script.min.js"></script>
</body>
</html>