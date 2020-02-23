<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Produkty</title>
</head>
<body>
<div>
    <p>Ilość dodanych produktów: ${currentOrder.order.soldProducts.size()}</p>
</div>
<h1>Lista produktów</h1>
<table>
    <tr>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Cena</th>
        <th>Ocena</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${allProducts}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.sellPrice}</td>
            <td>${product.raiting}</td>
            <td>
                <form method="post" action="/orders/add-product">
                    <input type="number" min="1" step="1" name="quantity" value="1"/>
                    <input type="hidden" name="productId" value="${product.id}"/>
                    <button type="submit">Dodaj</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
