<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zamówienia</title>
</head>
<body>
Zamówienia
<c:forEach items="${orders}" var="order" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <td><b>${order.createdOn}</b></td>
        <td>${order.price}</td>
        <td>
            <form method="post" action="/orders/lookupOrder">
                <input type="hidden" name="id" value="${order.id}"/>
                <button type="submit" name="open">podglad</button>
            </form>
        </td>
    </tr>
</c:forEach>

<footer></footer>
</body>
</html>
