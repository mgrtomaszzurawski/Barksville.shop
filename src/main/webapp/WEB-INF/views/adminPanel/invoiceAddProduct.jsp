<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>


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
<jsp:include page="invoice_menu.jsp"/>
<table>
<tr>
    <th>nr</th>
    <th>nazwa</th>
    <th>ilosc</th>
    <th>Cena sprzedarzy</th>
    <th>Usuń</th>
</tr>
<c:forEach items="${products}" var="product" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <td><b>${product.product.name}</b></td>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}">
                <input type="hidden" name="name" value="${product.product.name}"/>
                <button type="submit" name="delete">>usun</button>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
<h1>Suma: ${sum}</h1>
<h1>Wprowadz produkt:</h1>
<div>

    <form method="post">

        <fieldset>
            <legend>Dane produktu</legend>
            <p>
                <label for="name">Nazwa produktu: </label><input class="name" id="name" onkeyup="myFunction()"
                                                                 type="text"
                                                                 name="name"
                                                                 placeholder="Search for names.."
                                                                 title="Type in a name"/>
            </p>

            <p>
                <label for="quantity">Ilość: </label><input id="quantity" type="number" placeholder="0.00" step="0.01"
                                                            name="quantity"/>
            </p>
            <p>
                <label for="price">Jednostkowa Cenna netto: </label><input id="price" type="number" placeholder="0.00" step="0.01"
                                                                    name="price"/>
            </p>
            <p>Stawka VAT:</p>


                <input type="radio" id="23" name="VAT" value="23"
                       checked>
                <label for="23">23%</label>

                <input type="radio" id="8" name="VAT" value="8">
                <label for="8">8%</label>

            <p>Czy dzielisz produkt na części:</p>


                <input type="radio" id="false" name="divide" value="false"
                       checked>
                <label for="false">Nie</label>

                <input type="radio" id="true" name="divide" value="true">
                <label for="true">Tak</label>


            <p>
                <label for="parts">Ile części: </label>
                <input id="parts" type="number" placeholder="0" step="1"
                                                            name="parts"/>
            </p>

            <div class="column">
                <div class="content">
                    <div class="field is-grouped">
                        <div class="control">
                            <button class="button is-success is-link" type="submit"
                                    name="upload">
                                Dodaj produkt
                            </button>
                        </div>
                    </div>

                </div>

            </div>

            <div class="column">
                <div class="content">
                    <div class="field is-grouped">
                        <div class="control">
                            <button class="button is-success is-link" type="submit"
                                    name="save">
                                Zapisz liste produktów
                            </button>
                        </div>
                    </div>

                </div>

            </div>
            <div class="column"></div>
        </fieldset>

    </form>
</div>

<table id="myUL">
<c:forEach items="${products2}" var="product" varStatus="stat">
        <tr><td id="ramka">${product}</td></tr>
</c:forEach>
</table>
<script>
    function myFunction() {

        var input, filter, ul, li, a, i, txtValue;
        input = document.getElementById("name");
        filter = input.value.toUpperCase();
        ul = document.getElementById("myUL");
        li = ul.getElementsByTagName("tr");
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("td")[0];
            txtValue = a.textContent || a.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    }
    




</script>


<footer></footer>
</body>
</html>
