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


<table>
    <tr>
        <th>Nr</th>
        <th>Data</th>
        <th>Nazwa</th>
        <th>Obrót</th>
        <th>Zysk netto</th>
        <th>Cena zakupu towarów</th>
        <th>Podgląd listy raportów miesięcznych:}</th>

    </tr>
    <c:forEach items="${yearReports}" var="yearReport" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${yearReport.reportDate}</td>
            <td>${yearReport.reportName}</td>
            <td>${yearReport.grossIncome}</td>
            <td>${yearReport.netIncome}</td>
            <td>${yearReport.expenses}</td>

            <td>
                <form method="Post" action="/admin/report-view/month-report-list-of-month">
                    <input type="hidden" name="monthReportList" value="${yearReport.monthReportList}"/>
                    <button type="submit" name="view">>Podgląd</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<footer></footer>
</body>
</html>
