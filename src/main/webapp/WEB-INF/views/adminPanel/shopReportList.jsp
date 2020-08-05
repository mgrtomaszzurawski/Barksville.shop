<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="invoiceHeader.jsp"/>
    <title>Rejestracja</title>
    <style>
        #ramka {
            border: 1px solid black;
            margin: 10px;
            padding: 10px;
            user-select: all;
        }

        table, td, th, tr {
            border: 1px solid black;
            width: max-content;
            border-collapse: collapse;
            text-align: center;
            padding: 10px;
        }

        #myUL {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        #myUL li a {
            border: 1px solid #ddd;
            margin-top: -1px; /* Prevent double borders */
            background-color: #f6f6f6;
            padding: 12px;
            text-decoration: none;
            font-size: 18px;
            color: black;

        }

        #myUL li a:hover:not(.header) {
            background-color: #eee;
        }
    </style>
</head>
<body>
<jsp:include page="invoiceMenu.jsp"/>
<h2>Lista raportów sklepowych</h2>
<table>
    <tr>
        <th>Nr</th>
        <th>Data</th>
        <th>Nazwa</th>
        <th>Ilość tranzakcji</th>
        <th>Obrót</th>
        <th>Skan</th>
        <th>Dodał</th>
        <th>Podgląd</th>

    </tr>
    <c:forEach items="${shopReportList}" var="shopReport" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${shopReport.date}</td>
            <td>${shopReport.name}</td>
            <td>${shopReport.transactionsNumber}</td>
            <td>${shopReport.earnings}</td>


            <td><c:if test="${not empty shopReport.shopReportScanFile}"><form method="post" action="/admin/shop-report/download" target="_blank">
                <input type="hidden" name="reportDate" value="${shopReport.date}"/>
                <button type="submit" name="download" >Pobierz</button>
            </form></c:if></td>
            <td><b>${shopReport.opr}</b></td>
            <td>
                <form method="post" action="/admin/shop-report/shop-report-view/" >
                    <input type="hidden" name="reportDate" value="${shopReport.date}"/>
                    <button type="submit" name="view">>Podgląd</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<footer></footer>
</body>
</html>
