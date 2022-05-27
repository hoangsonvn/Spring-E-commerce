<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
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
          type="text/css" media="screen"/>
    <link href="../resource/client/css/sequence-looptheme.css"
          rel="stylesheet" media="all"/>
    <link href="../resource/client/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script><![endif]-->
</head>
<body id="home">
<div class="wrapper">
    <%@include file="common/header.jsp" %>

    <div class="container_fullwidth">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="category leftbar">
                        <h3 class="title">Categories</h3>
                        <ul>
                            <c:forEach items="${categories}" var="category">
                                <li><a
                                        href="<c:url value="/client/product-grid?categoryId=${category.categoryId}"/>">${category.categoryName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="price-filter leftbar">
                        <h3 class="title">Price</h3>
                        <form class="pricing" action="search" method="get">
                            <select style="color:#ff1e2c;" name="pricing" onchange='this.form.submit()'>
                                <option
                                        <c:if test="${pricing eq 'default'}">
                                            selected="selected"
                                        </c:if>
                                        value="default">Find by price
                                </option>
                                <option
                                        <c:if test="${pricing eq 'under15'}">
                                            selected="selected"
                                        </c:if>
                                        value="under15">under 15 dollars
                                </option>
                                <option
                                        <c:if test="${pricing eq '15to50'}">
                                            selected="selected"
                                        </c:if>
                                        value="15to50">15 dollars to 50 dollars
                                </option>
                                <option
                                        <c:if test="${pricing eq 'greaterthan50'}">
                                            selected="selected"
                                        </c:if>
                                        value="greaterthan50">greater than 50 dollars
                                </option>
                                <input type="hidden" name="categoryId" value="${categoryId}"/>
                                <input type="hidden" name="text" value="${text}"/>
                                <input type="hidden" value="${pageSize}" name="pageSize"/>
                                <input type="hidden" value="${sort}" name="sort"/>
                            </select>
                        </form>
                    </div>

                    <div class="clearfix"></div>
                    <div class="others leftbar">
                        <h3 class="title">Others</h3>
                    </div>
                    <div class="clearfix"></div>
                    <div class="fbl-box leftbar">
                        <h3 class="title">Facebook</h3>
                        <span class="likebutton"> <a href="#"> <img
                                src="../resource/client/images/fblike.png" alt="">
							</a>
							</span>
                        <p>99999k like Pi Halo ~</p>
                        <ul>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                            <li><a href="#"> </a></li>
                        </ul>
                        <div class="fbplug">
                            <a href="#"> <span> <img
                                    src="../resource/client/images/fbicon.png" alt="">
								</span> Facebook social plugin
                            </a>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="leftbanner">
                        <img src="../resource/client/images/vans.jpg" alt="">
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="clearfix"></div>
                    <div class="products-grid">

                        <!-- Tool Bar -->
                        <jsp:include page="common/toolbar.jsp"/>

                        <div class="clearfix"></div>
                        <div class="row">
                            <c:forEach items="${products}" var="product">
                                <div class="col-md-4 col-sm-4">
                                    <div class="products">
                                        <c:if test="${product.saleDTO.salePercent != 0}">
                                            <div class="offer">-${product.saleDTO.salePercent}%</div>
                                        </c:if>
                                        <div class="thumbnail">
                                            <a href="<c:url value="/client/product-details?productId=${product.productId}"/>"><img
                                                    style="width: 90%;"
                                                    src="<c:url value="/download?image=${product.image}"/>" alt="Product Name"></a>
                                        </div>
                                        <div class="productname">${product.productName}</div>

                                        <c:if test="${product.saleDTO.salePercent == 0}">
												<span class="price"
                                                      style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">---</span>
                                            <span class="price">$${Math.round(product.price - (product.price
                                                    * product.saleDTO.salePercent / 100))}.0</span>
                                        </c:if>

                                        <c:if test="${product.saleDTO.salePercent != 0}">
												<span class="price"
                                                      style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">$${product.price}</span>
                                            <span class="price">$${Math.round(product.price - (product.price
                                                    * product.saleDTO.salePercent / 100))}.0</span>
                                        </c:if>

                                        <div class="button_group">
                                            <form action="<c:url value="/client/addcart"/> " method="post">
                                                <button class="button add-cart" type="submit">Add To Cart</button>
                                                <input value="${product.productId}" name="productId" type="hidden">
                                                <a class="button wishlist" type="button" href="<c:url value="/client/like/${product.productId}"/> ">
                                                    <i class="fa fa-heart-o"> </i>
                                                </a>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="clearfix"></div>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="common/footer.jsp" %>
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