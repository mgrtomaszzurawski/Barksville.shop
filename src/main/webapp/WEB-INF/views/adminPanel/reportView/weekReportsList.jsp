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


<table>
    <tr>
        <th>Nr</th>
        <th>Data</th>
        <th>Nazwa</th>
        <th>Obrót</th>
        <th>Zysk netto</th>
        <th>Cena zakupu towarów</th>
        <th>Podgląd listy raportów dziennych z tygonia</th>

    </tr>
    <c:forEach items="${weekReports}" var="weekReport" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${weekReport.reportDate}</td>
            <td>${weekReport.reportName}</td>
            <td>${weekReport.grossIncome}</td>
            <td>${weekReport.netIncome}</td>
            <td>${weekReport.expenses}</td>

            <td>
                <form method="Post" action="/admin/report-view/day-report-list-of-week">
                    <input type="hidden" name="reportDate" value="${weekReport.reportDate}"/>
                    <button type="submit" name="view">>Podgląd</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<footer></footer>
</body>
</html>
