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
<table>
    <tr>
        <th>Nr</th>
        <th>Firma</th>
        <th>Data wystawienia</th>
        <th>Nr faktury</th>
        <th>Skan</th>
        <th>Dodał</th>
        <th>Podgląd</th>

    </tr>
    <c:forEach items="${invoices}" var="invoice" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td><b>${invoice.company}</b></td>
            <td>${invoice.date}</td>
            <td>${invoice.invoiceNumber}</td>

            <td><c:if test="${not empty invoice.invoiceScanFile}"><form method="post" action="/admin/invoice-list/download" target="_blank">
                <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
                <button type="submit" name="download" >Pobierz</button>
            </form></c:if></td>
            <td><b>${invoice.opr}</b></td>
            <td>
                <form method="post" action="/admin/invoice-list/invoice/" >
                    <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
                    <button type="submit" name="view">>Podgląd</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<footer></footer>
</body>
</html>
