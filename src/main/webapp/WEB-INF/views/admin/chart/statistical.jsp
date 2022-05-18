<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                                                    <a class="btn btn-warning"
                                                       href="<c:url value="/admin/statistical"/>"
                                                       style="background-color: #D9534F; padding: 2px 10px; text-decoration: none; border: none; margin-right: 10px; height: 25px;">Back</a>
                                                    <form action="<c:url value="/admin/statistical"/> " method="get>">
                                                        <div class="right">
                                                            <span style="color: #d5641c">MONTH</span>
                                                            <select name="month">
                                                                <c:forEach begin="1" end="12" var="i">
                                                                    <option selected="selected"
                                                                            value="${i}">${i}</option>
                                                                </c:forEach>
                                                                <option selected="selected"></option>

                                                            </select>
                                                            <span style="color: #d5641c"> YEAR</span>
                                                            <select name="year">
                                                                <c:forEach begin="${first}" end="${last}" var="i">
                                                                    <option selected="selected"
                                                                            value="${i}">${i}</option>
                                                                </c:forEach>
                                                                <option selected="selected"></option>

                                                            </select>
                                                            <%--
                                                                                                                        <input name="pageIndex" value="${pageIndex}" type="hidden">
                                                            --%>
                                                            <button style="color: #2bb24c;background: #abdbe3" type="submit" class="btn btn-secondary">Submit
                                                            </button>
                                                             <a style="color: #e7357a" href="<c:url value="/admin/export?month=${month}&year=${year}"/> " class="btn btn-info">Export
                                                        </a>

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
                                                        <th style=" color: #abdbe3; font-weight: bold;">Name</th>
                                                        <th style=" color: #abdbe3; font-weight: bold;">TotalQuantity
                                                        </th>
                                                        <th style=" color: #abdbe3; font-weight: bold;">TotalPrice</th>
                                                        <th style=" color: #abdbe3; font-weight: bold;">Average Price
                                                        </th>
                                                        <th style=" color: #abdbe3; font-weight: bold;">Min Price</th>
                                                        <th style=" color: #abdbe3; font-weight: bold;">Max Price</th>
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
                                        <c:if test="${totalPage !=null }">
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