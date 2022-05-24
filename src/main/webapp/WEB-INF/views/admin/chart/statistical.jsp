<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>
<!-- WRAPPER -->
<div id="wrapper">

    <jsp:include page="../common/header.jsp"/>

    <jsp:include page="../common/category.jsp"/>

    <!-- MAIN -->
    <!-- MAIN CONTENT -->
    <div class="main">
        <div class="main-content">
            <div class="container-fluid">
                <!-- OVERVIEW -->
                <div class="panel panel-headline">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="panel">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel">
                                                <div class="panel-heading">

                                                    <form action="<c:url value="/admin/statistical"/> " method="get>">
                                                        <div class="right">
                                                            <span style="color: #00c300">MONTH</span>
                                                            <select name="month">
                                                                <option
                                                                        <c:if test="${month == null}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                ></option>
                                                                <c:forEach begin="1" end="12" var="i">

                                                                    <option  <c:if test="${month == i}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                            value="${i}">${i}</option>
                                                                </c:forEach>
                                                                <%--
                                                                                                                                <option selected="selected"></option>
                                                                --%>

                                                            </select>
                                                            <span style="margin-left:10px;color: #fd434b"> YEAR</span>
                                                            <select name="year">
                                                                <option
                                                                        <c:if test="${year == null}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                ></option>
                                                                <c:forEach begin="${first}" end="${last}" var="i">
                                                                    <option
                                                                            <c:if test="${year == i}">
                                                                                selected="selected"
                                                                            </c:if>
                                                                            value="${i}">${i}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <button style="width:50px;margin-left:15px;color: #EFEFEF;background: #002663"
                                                                    type="submit" class="btn btn-secondary"> Submit
                                                            </button>
                                                            <a style=" size: 15px; margin-left:20px;color: #008855;font-weight: bolder"
                                                               href="<c:url value="/admin/export?month=${month}&year=${year}"/> "
                                                               class="btn btn-secondary btn-sm">Export</a>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="panel-body no-padding">
                                                <table class="table table-striped" style="margin: auto;">
                                                    <thead>
                                                    <tr>
                                                        <th><input type="checkbox" name="all" id="checkAll"
                                                                   style="cursor: pointer;"/></th>
                                                        <th style=" color: #36007a; font-weight: bold;">Name</th>
                                                        <th style=" color: #36007a; font-weight: bold;">TotalQuantity
                                                        </th>
                                                        <th style=" color: #36007a; font-weight: bold;">TotalPrice</th>
                                                        <th style=" color: #36007a; font-weight: bold;">Average Price
                                                        </th>
                                                        <th style=" color: #36007a; font-weight: bold;">Min Price</th>
                                                        <th style=" color: #36007a; font-weight: bold;">Max Price</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:if test="${stats != null}">
                                                        <c:forEach items="${stats}" var="stat" varStatus="loop">
                                                            <tr>
                                                                <td style="vertical-align: middle;"><input
                                                                        class="checkbox" type="checkbox" name="orderId"
                                                                        style="cursor: pointer;"/></td>
                                                                <td style="vertical-align: middle; color: #41A9; font-weight: bold;">${stat.name}</td>
                                                                <td style="color: #41A9; font-weight: bold;">${stat.quantity}</td>
                                                                <td style="color: #41A9; font-weight: bold;">
                                                                    $${Math.round(stat.totalprice)}.00
                                                                </td>
                                                                <td style="color: #41A9; font-weight: bold;">${Math.round(stat.avgprice)}</td>
                                                                <td style="color: #41A9; font-weight: bold;">${Math.round(stat.minprice)}</td>
                                                                <td style="color: #41A9; font-weight: bold;">${Math.round(stat.maxprice)}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <c:if test="${totalPage != 0}">

                                            <nav aria-label="Page navigation example"
                                                 style="margin-top: -30px;">
                                                <ul class="pagination">
                                                    <c:forEach begin="0" end="${totalPage - 1}" var="i">
                                                        <li class="page-item"><a class="page-link"
                                                                <c:if test="${i == pageIndex}">
                                                                    style="background-color: #F0AD4E; color: white;"
                                                                </c:if>
                                                                                 href="<c:url value="/admin/statistical?month=${month}&year=${year}&pageIndex=${i}"/>">${i + 1}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </nav>
                                        </c:if>
                                    </div>
                                    <div id="headline-chart" class="ct-chart"></div>


                                </div>
                            </div>
                        </div>
                        <!-- END OVERVIEW -->
                    </div>
                    <!-- END MAIN CONTENT -->
                </div>


                <div id="chartContainer" style="height: 370px; width: 100%;"></div>

                <script type="text/javascript">
                    window.onload = function () {

                        var dps = [[]];
                        var chart = new CanvasJS.Chart("chartContainer", {
                            animationEnabled: true,
                            exportEnabled: true,
                            title: {
                                text: "Pie Chart"
                            },
                            data: [{
                                type: "pie",
                                yValueFormatString: "#,###\"%\"",
                                showInLegend: true,
                                indexLabel: "{y}",
                                indexLabelPlacement: "inside",
                                dataPoints: dps[0]
                            }]
                        });

                        var yValue;
                        var name;

                        <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
                        <c:forEach items="${dataPoints}" var="dataPoint">
                        yValue = parseFloat("${dataPoint.y}");
                        name = "${dataPoint.name}";
                        dps[parseInt("${loop.index}")].push({
                            name: name,
                            y: yValue
                        });
                        </c:forEach>
                        </c:forEach>

                        chart.render();


                    }
                </script>


                <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

                <!-- Javascript -->

            </div>
        </div>
    </div>
</div>
</body>
</html>