<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<nav class="light-blue">
    <div class="nav-wrapper container" role="navigation">
        <a id="logo-container" href="/">Strona Główna</a>
        <c:if test="${not empty sessionScope['user']}">
            <span> (${sessionScope['user'].login} )</span>
        </c:if>

        <ul class="right">
            <sec:authorize access="isAuthenticated()">
                <li><a href="/logout">Wyloguj</a></li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="/admin/panel">Panel administracyjny</a></li>
            </sec:authorize>
        </ul>

    </div>
</nav>
