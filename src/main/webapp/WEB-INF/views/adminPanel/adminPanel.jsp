<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="../elements/header.jsp"/>
</head>
<body>
<jsp:include page="../elements/menu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu administratora</h2>
        <table>
            <tr>
                <th>Wprowadz</th>
                <th>Podgląd</th>
            </tr>

            <tr>
                <th>
                    <form method="get" action="/admin/invoice">
                        <button type="submit">
                            wprowadz fakture
                        </button>
                    </form>
                </th>
                <th>
                    <form method="get" action="/admin/invoice-list">
                        <button type="submit">
                            lista faktur
                        </button>
                    </form>
                </th>
            </tr>

            <tr>
                <th>
                    <form method="get" action="/admin/shop-report">
                        <button type="submit">
                            wprowadz raport sklepowy
                        </button>
                    </form>
                </th>
                <th>
                    <form method="get" action="/admin/shop-report/list">
                        <button type="submit">
                            pokarz liste raportów sklepowych
                        </button>
                    </form>
                </th>
            </tr>


            <tr>
                <th>
                    <form method="get" action="/admin/products">
                        <button type="submit">
                            Stan produktów
                        </button>
                    </form>
                </th>
                <th>
                    <form method="get" action="/admin/products/product-alias-list">
                        <button type="submit">
                            Dodaj alias do produktu
                        </button>
                    </form>
                </th>
            </tr>


            <tr>
                <th>
                    <form method="get" action="/admin/products/low-quantity-products">
                        <button type="submit">
                           Produkty do kupienia
                        </button>
                    </form>
                </th>
                <th>
                    <form method="get" action="/admin/products/product-minimal-quantity-list">
                        <button type="submit">
                            Ustaw minimalne ilości produktów
                        </button>
                    </form>
                </th>
            </tr>


            <tr>
                <th>
                    <form method="get" action="/admin/report-create">
                        <button type="submit">
                            Utwórz raport
                        </button>
                    </form>
                </th>
                <th>
                    <form method="get" action="/admin/report-view">
                        <button type="submit">
                            przeglądaj raporty
                        </button>
                    </form>
                </th>
            </tr>

        </table>


    </div>

</div>
</body>
</html>

