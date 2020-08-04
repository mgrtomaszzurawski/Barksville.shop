<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Check Product</title>
    <jsp:include page="invoiceHeader.jsp"/>
</head>
<body>
<jsp:include page="invoiceMenu.jsp"/>
<c:forEach items="${nonExisting}" var="nonExist" varStatus="stat">
<tr>
    <td>nr</td>
    <td>nazwa</td>
    <td>cena na fakturze</td>
    <td>Podaj cene sprzedarzy na sklepu online</td>
    <td></td>
</tr>
<form method="post" action="${pageContext.request.contextPath}">
    <tr>
        <td>${stat.count}</td>
        <td>${nonExist.name}</td>
        <td>${nonExist.invoicePriceList.get(0).invoicePrice.toString()}</td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}">
                <input id="price" type="number" placeholder="0.00" step="0.01" name="price"/>
                <input type="hidden" name="name" value="${nonExist.name}"/>
                <button type="submit" name="upload">Dodaj</button>
            </form>
        </td>


    </tr>


    <button type="submit" name="update"></button>
</form>
</c:forEach>


<c:forEach items="${existing}" var="exist" varStatus="stat">
<tr>
    <td>nr</td>
    <td>nazwa</td>
    <td>cena na fakturze</td>
    <td></td>
</tr>
    <tr>
        <td>${stat.count}</td>
        <td>${exist.name}</td>
        <td>${exist.invoicePriceList.get(0).invoicePrice}</td>

    </tr>
    </c:forEach>


<header>
    <h1>Rejestracja</h1>
</header>
<section>
    <h1>Wypełnij dane rejestracyjne</h1>
    <div>


                <div class="column">
                    <div class="content">
                        <div class="field is-grouped">
                            <div class="control">
                                <form method="post">
                                <button  type="submit" name="save">
                                    Zapisz
                                </button>
                                </form>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column"></div>
    </div>
</section>
<footer></footer>
</body>
</html>
