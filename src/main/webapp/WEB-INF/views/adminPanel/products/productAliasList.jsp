<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../invoiceHeader.jsp"/>
    <style>
        table, td, th, tr {
            border: 1px solid black;
            width: max-content;
            border-collapse: collapse;
        }

    </style>
    <title>Rejestracja</title>
</head>
<body>
<jsp:include page="../../elements/menu.jsp"/>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

<table>
    <tr>
        <th>nr</th>
        <th>nazwa</th>
        <th>Aliasy</th>
        <th>Dodaj alias</th>

    </tr>

    <c:forEach items="${products}" var="product" varStatus="stat">

        <tr>
            <td>${stat.count}</td>
            <td>${product.name}</td>
            <td>${product.aliasNames}</td>

                <td>
                    <form method="post" action="/admin/products/product-alias-list">
                    <label for="alias">Minimalna ilość: </label>
                    <input type="hidden" name="id" value="${product.id}"/>
                    <input id="alias"
                           type="text"
                           name="alias" placeholder="Wpisz nowy alias"
                           title="Type in new alias"/>
                    <button type="submit" name="add">
                        zapisz
                    </button>
                    </form>
                </td>

        </tr>

    </c:forEach>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">

    <li><a href="/admin/panel">Panel administracyjny</a></li>

</sec:authorize>


<footer></footer>
</body>
</html>
