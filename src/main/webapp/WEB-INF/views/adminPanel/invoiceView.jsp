<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="invoice_header.jsp"/>
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
<jsp:include page="invoice_menu.jsp"/>
<h1>Faktura nr: ${invoice.invoiceNumber}</h1>
<p>Firma: ${invoice.company}</p>
<p>Data wystawienia: ${invoice.date}</p>
<p>Wprowadził: ${invoice.opr}</p>
<p>Suma na Fakturze: ${invoice.cost}</p>
<p>
<form method="post" action="/admin/invoice-list/invoice/data">
    <input type="hidden" name="name" value="${invoice.invoiceNumber}"/>
    <button type="submit" name="edit">Edytuj fakturę/TODO</button>
</form>
<form method="post" action="/admin/invoice-list/invoice/data">
    <input type="hidden" name="name" value="${invoice.invoiceNumber}"/>
    <button type="submit" name="edit">Usuń fakture/TODO</button>
</form>
</p>


<table>
    <tr>
        <th>Nr</th>
        <th>Nazwa</th>
        <th>Ilosc</th>
        <th>Cena netto</th>
        <th>vat</th>
        <th>Podzielona</th>
        <th>Cześci</th>
        <th>Jednostkowa cena brutto</th>
        <th>Edytuj</th>
        <th>Edytuj</th>
    </tr>
    <c:forEach items="${invoice.boughtProducts}" var="product" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${product.product.name}</td>
            <td>${product.quantity}</td>
            <td>${product.nettoPrice}</td>
            <td>${product.vat}</td>
            <td>${product.isDivided}</td>
            <td>${product.parts}</td>
            <td>${product.price}</td>


            <td>
                <form method="post" action="/admin/invoice-list/invoice/row">
                    <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
                    <input type="hidden" name="id" value="${product.id}"/>
                    <button type="submit" name="edit">Edytuj</button>
                </form>
            </td>
            <td>
                <form method="post" action="/admin/invoice-list/invoice/row">
                    <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
                    <input type="hidden" name="id" value="${product.id}"/>
                    <button type="submit" name="delete">Usuń/TODO</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="/admin/invoice-list/invoice/row">
    <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
    <button type="submit" name="add">Dodaj/TODO</button>
</form>

<footer></footer>
</body>
</html>
