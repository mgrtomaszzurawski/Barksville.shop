<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="light-blue">
    <div class="nav-wrapper container" role="navigation">
        <a id="logo-container" href="/">Strona Główna</a>
        <c:if test="${not empty sessionScope['user']}">
            <span> (${sessionScope['user'].login} )</span>
        </c:if>
        <ul class="right">

            <!-- usunąc 1 z role-->
                <sec:authorize access="hasRole('ROLE_USER1')">
                <li><a href="/user/skills">Twoje zamówienia</a></li>
                <li><a href="/user/sources">Twoje konto</a></li>
            </sec:authorize>
        </ul>
        <ul class="right">
            <sec:authorize access="isAnonymous()">
                <li><a href="/login">Zaloguj</a></li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li><a href="/logout">Wyloguj</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_RRGISTRY')">
                <li><a href="/register">Rejestracja</a></li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">

                <li><a href="/admin/panel">Panel administracyjny</a></li>

            </sec:authorize>
        </ul>

    </div>
</nav>
