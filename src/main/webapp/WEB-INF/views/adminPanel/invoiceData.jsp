<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="invoiceHeader.jsp"/>
    <title>Rejestracja</title>
    <style>
        #ramka{
            border: 1px solid black;
            margin:10px;
            padding: 10px;
            user-select: all;
        }
        table, td, th, tr {
            border: 1px solid black;
            width: max-content;
            border-collapse: collapse;
            text-align:center;
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
<h1 style="color: red">Trzeba uzupełnic wszystkie pola!</h1>
<p>Faktura nr: ${invoice.invoiceNumber}</p>
<p>Firma: ${invoice.company}</p>
<p>Data wystawienia: ${invoice.date}</p>
<p>Wprowadził: ${invoice.opr}</p>
<p>Suma na Fakturze: ${invoice.cost}</p>

<form method="post">
    <input type="hidden" name="oldInvoiceNumber" value="${invoice.invoiceNumber}"/>
    <fieldset>
        <legend>Dane Faktury</legend>
        <p>
            <label for="company">Nazwa firmy: </label><input id="company" type="text"
                                                             name="company"/>
        </p>
        <p>
            <label for="invoiceNumber">Nr faktury: </label><input id="invoiceNumber" type="text"
                                                                  name="invoiceNumber"/>
        </p>
        <p>
            <label for="invoiceDate">Data wystawienia faktury: </label><input id="invoiceDate" type="date"
                                                                              name="invoiceDate" />
        </p>
        <p>
            <label for="cost">Suma na fakturze: </label><input id="cost" type="number" placeholder="0.00" step="0.01"
                                                               name="cost" />
        </p>

        <div class="column">
            <div class="content">
                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-success is-link" type="submit"
                                name="change">
                            Zapisz zmiany
                        </button>
                    </div>
                </div>

            </div>

        </div>
        <div class="column"></div>
    </fieldset>

</form>



<form method="post" action="/admin/invoice-list/invoice">
    <input type="hidden" name="invoiceNumber" value="${invoice.invoiceNumber}"/>
    <button type="submit" name="edit">Wróć</button>
</form>



<footer></footer>
</body>
</html>
