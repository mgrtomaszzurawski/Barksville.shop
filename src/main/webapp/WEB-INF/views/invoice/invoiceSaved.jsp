<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zapisano Pomyślnie</title>
</head>
<body>

<header>
    <h1>Zpowrot do panelu</h1>
</header>
<sec:authorize access="hasRole('ROLE_ADMIN')">

    <li><a href="/admin/panel">Panel administracyjny</a></li>

</sec:authorize>


</body>
</html>
