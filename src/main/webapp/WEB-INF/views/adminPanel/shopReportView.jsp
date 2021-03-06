<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>

    <title>Lista Raportów Dziennych</title>
    <jsp:include page="../elements/header.jsp"/>


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
<jsp:include page="../elements/menu.jsp"/>

<h2>Dane</h2>
<table>
    <tr>
        <th>Data</th>
        <th>Nazwa</th>
        <th>Ilość tranzakcji</th>
        <th>Obrót</th>
        <th>Pobierz raport</th>
        <th>Dodał</th>


    </tr>
    <tr>
        <td>${shopReport.date}</td>
        <td>${shopReport.name}</td>
        <td>${shopReport.earnings}</td>
        <td>${shopReport.transactionsNumber}</td>
        <td><c:if test="${not empty shopReport.shopReportScanFile}"><form method="post" action="/admin/shop-report/download" target="_blank">
            <input type="hidden" name="reportDate" value="${shopReport.date}"/>
            <button type="submit" name="download" >Pobierz</button>
        </form></c:if></td>
        <td>${shopReport.opr}</td>
    </tr>

</table>

<h2>Lista sprzedanych przedmiotów sprzedanych w ${shopReport.name}</h2>
<table>
    <tr>
        <th>Nr</th>
        <th>Nazwa</th>
        <th>Ilość</th>
        <th>Cena</th>
    </tr>
    <c:forEach items="${shopReport.soldProducts}" var="product" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${product.product.name}</td>
            <td>${product.quantity}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>


<footer></footer>
</body>
</html>
