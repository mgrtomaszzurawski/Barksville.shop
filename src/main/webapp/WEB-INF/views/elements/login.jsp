<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona logowania</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container">
    <div class="row center"><h2>Formularz logowania</h2>
        <div class="col s2"></div>
        <div class="col s6">
        <form method="post" action="/login">
            <div>
                <fieldset>
                    <legend>Dane logowania</legend>
                    <div><label for="username">Login: </label><input type="text" required name="username"
                                                                  id="username"/></div>
                    <div><label for="password">Password</label><input type="password" required
                                                                      name="password"
                                                                      id="password"/></div>
                    <div>
                        <button type="submit" class="btn waves-effect waves-light">Zaloguj</button>
                    </div>
                </fieldset>
            </div>
        </form>
        </div>
        <div class="col s4"></div>
    </div>
</div>
</body>
</html>
