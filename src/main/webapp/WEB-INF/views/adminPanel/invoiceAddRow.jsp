<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>


    <title>Rejestracja</title>

</head>
<body>
<jsp:include page="invoice_menu.jsp"/>

<h1 style="color: red">Trzeba wypelnic wszystkie pola!!!</h1>
<form method="post" action="/admin/invoice-list/invoice/row">

    <fieldset>
        <table>
            <tr>

                <th>Nazwa</th>
                <th>Ilosc</th>
                <th>Cena netto</th>
                <th>vat</th>
                <th>Podzielona</th>
                <th>Cześci</th>
                <th>Jednostkowa cena brutto</th>
                <th>Edytuj</th>
            </tr>

            <tr>

                <td>${row.product.name}</td>
                <td>${row.quantity}</td>
                <td>${row.nettoPrice}</td>
                <td>${row.vat}</td>
                <td>${row.isDivided}</td>
                <td>${row.parts}</td>
                <td>${row.price}</td>
                <td>
                    <form method="post" action="/admin/invoice-list/invoice/row">
                        <input type="hidden" name="invoiceNumber" value="${invoiceNumber}"/>

                        <button type="submit" name="delete">usuń</button>
                    </form>
                </td>
            </tr>


            <tr>

                <td><label for="name"></label><input class="name" id="name" onkeyup="myFunction()"
                                                                     type="text"
                                                                     name="name"
                                                                     placeholder="Search for names.."
                                                                     title="Type in a name"/></td>
                <td><label for="quantity"></label><input id="quantity" type="number" placeholder="0.00" step="0.01"
                                                         name="quantity"/></td>
                <td><label for="nettoPrice"></label><input id="nettoPrice" type="number" placeholder="0.00" step="0.01"
                                                           name="nettoPrice"/></td>
                <td>
                    <input type="radio" id="23" name="vat" value="0.23"
                           checked>
                    <label for="23">23%</label>

                    <input type="radio" id="8" name="vat" value="0.08">
                    <label for="8">8%</label></td>
                <td><input type="radio" id="false" name="isDivided" value="false"
                           checked>
                    <label for="false">Nie</label>

                    <input type="radio" id="true" name="isDivided" value="true">
                    <label for="true">Tak</label></td>
                <td><label for="parts"></label>
                    <input id="parts" type="number" placeholder="0" step="1"
                           name="parts"/></td>
                <td><label for="price"></label><input id="price" type="number" placeholder="0.00" step="0.01"
                                                      name="price"/></td>
                <td>


                    <button type="submit" name="update">zmień</button>

                </td>
            </tr>
        </table>
    </fieldset>
</form>


<form method="post" action="/admin/invoice-list/invoice">
    <input type="hidden" name="invoiceNumber" value="${invoiceNumber}"/>
    <button type="submit" name="back">wróć</button>
</form>

<footer></footer>
</body>
</html>
