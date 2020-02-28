<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<div>
    <tr>
        <td>nr</td>
        <td>nazwa</td>
        <td>aktywny</td>
        <td>Cena sprzedarzy</td>
        <td>ilosc</td>
    </tr>
</div>
<c:forEach items="${products}" var="product" varStatus="stat">
    <div>
        <tr>
            <td>${stat.count}</td>
            <td>${product.name}</td>
            <td>${product.state}</td>
            <td>${product.sellPrice}</td>
            <td>${product.quantity}</td>
        </tr>
    </div>
</c:forEach>

<sec:authorize access="hasRole('ROLE_ADMIN')">

    <li><a href="/admin/panel">Panel administracyjny</a></li>

</sec:authorize>
<footer></footer>
</body>
</html>
