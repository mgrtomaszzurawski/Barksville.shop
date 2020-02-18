<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="light-blue">
    <div class="nav-wrapper container" role="navigation">
        <a id="logo-container" href="/">Kategorie</a>
        <c:if test="${not empty sessionScope['user']}">
            <span> (${sessionScope['user'].login} )</span>
        </c:if>
        <ul class="right">
            <li><a href="/">Strona główna</a></li>
            <li><a href="/user/skills">Twoje zamówienia</a></li>
            <li><a href="/user/sources">Źrodła wiedzy</a></li>
        </ul>
        <ul class="right">
            <li><a href="/login">Zaloguj</a></li>
            <li><a href="/logout">Wyloguj</a></li>
            <li><a href="/register">Rejestracja</a></li>
        </ul>
    </div>
</nav>
