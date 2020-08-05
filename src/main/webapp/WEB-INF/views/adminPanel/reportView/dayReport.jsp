<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>

    <title>Lista Raportów Dziennych</title>
    <jsp:include page="reportViewHeader.jsp"/>


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
<jsp:include page="reportViewMenu.jsp"/>


<h2>Lista sprzedanych przedmiotów</h2>
<table>
    <tr>
        <th>Nr</th>
        <th>Nazwa</th>
        <th>Ilość</th>
        <th>Obrót</th>
        <th>Zysk netto</th>
        <th>Cena zakupu towaru</th>

    </tr>
    <c:forEach items="${dayReport.soldItemReportList}" var="soldItem" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${soldItem.soldInvoiceItem.product.name}</td>
            <td>${soldItem.quantity}</td>
            <td>${soldItem.grossIncome}</td>
            <td>${soldItem.netIncome}</td>
            <td>${soldItem.soldInvoiceItem.price}</td>
        </tr>
    </c:forEach>
</table>

<h2>Podsumowanie</h2>
<table>
    <tr>
        <th>Data</th>
        <th>Nazwa</th>
        <th>Obrót</th>
        <th>Zysk netdto</th>
        <th>Cena zakupu towarów</th>
        <th>Podgląd raportu z kasy</th>


    </tr>
    <tr>
        <td>${dayReport.reportDate}</td>
        <td>${dayReport.reportName}</td>
        <td>${dayReport.grossIncome}</td>
        <td>${dayReport.netIncome}</td>
        <td>${dayReport.expenses}</td>
        <td>
            <form method="post" action="/admin/shop-report/shop-report-view">
                <input type="hidden" name="reportDate" value="${dayReport.reportDate}"/>
                <button type="submit" name="view">>Podgląd</button>
            </form>
        </td>

    </tr>

</table>

<footer></footer>
</body>
</html>
