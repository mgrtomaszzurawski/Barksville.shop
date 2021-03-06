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
      <%--  <<td><b>${product.product.name}</b></td>--%>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
        <%--<td>
            <form method="post" action="${pageContext.request.contextPath}">
                <input type="hidden" name="name" value="${product.product.name}"/>
                <button type="submit" name="delete">>usun</button>
            </form>
        </td>--%>
    </tr>
</c:forEach>



    <div>

        <form method="post">
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
                                <button type="submit"
                                        name="save">
                                   złóż zamowienie
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column"></div>


        </form>
    </div>

<footer></footer>
</body>
</html>
