<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<c:forEach items="${soldProducts}" var="product" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <%--<td><b>${product.product.name}</b></td>--%>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
    </tr>
</c:forEach>


    <div>


            <c:if test="${not empty errors}">
                <fieldset>
                    <legend>Błędy</legend>
                    <c:forEach items="${errors}" var="error" varStatus="stat">
                        <p>
                                ${stat.count}. ${error}
                        </p>
                    </c:forEach>
                </fieldset>
            </c:if>


                <div class="column">
                    <div class="content">
                        <div class="field is-grouped">
                            <div class="control">
                                <form method="get" action="/orders" >
                                <button type="submit">
                                   Wroć do zamowień
                                </button>
                                </form>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column"></div>



    </div>

<footer></footer>
</body>
</html>
