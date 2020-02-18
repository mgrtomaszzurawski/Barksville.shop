 <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
    <jsp:include page="elements/header.jsp"/>
</head>
<body>
<jsp:include page="elements/menu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś na stronie sklepu Barksville</h2>
        <table>
            <thead>
            <tr>
                <th>Pozycja</th>
                <th>Login</th>
                <th>Unikalne umiejętności</th>
                <th>Wszystkie potwierdzenia umiejętności</th>
                <th>Liczba znanych źródeł</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${topUsers}" var="topUser" varStatus="status">
                <tr>
                    <td>${status.index + 1}.</td>
                    <td>${topUser.login}</td>
                    <td>${topUser.uniqueSkillsCount}</td>
                    <td>${topUser.allSkillsCount}</td>
                    <td>${topUser.knowledgeSourceCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="divider"></div>
    <div class="row center">
        <h2>Najlepsi w popularnych umiejętnościach</h2>
        <table>
            <thead>
            <tr>
                <th>Pozycja</th>
                <th>Umiejętność</th>
                <th>Kategoria</th>
                <th>Najlepszy użytkownik</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${topSkills}" var="topSkill" varStatus="status">
                <tr>
                    <td>${status.index + 1}.</td>
                    <td>${topSkill.name}</td>
                    <td>${topSkill.category}</td>
                    <td>${topSkill.bestUser}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="divider"></div>
</div>
</body>
</html>
