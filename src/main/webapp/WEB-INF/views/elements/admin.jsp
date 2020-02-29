<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu administratora</h2>
        <form method="get" action="/admin/invoice">
            <button  type="submit" >
               wprowadz fakture
            </button>
        </form>
        <form method="get" action="/admin/products">
            <button  type="submit" >
                sprawdz produkty
            </button>
        </form>
        <form method="get" action="/admin/shop-raport">
            <button  type="submit" >
                wprowadz raport sklepowy
            </button>
        </form>
    </div>
    <div class="divider"></div>
    <div class="row center">

    </div>
    <div class="divider"></div>
</div>
</body>
</html>

