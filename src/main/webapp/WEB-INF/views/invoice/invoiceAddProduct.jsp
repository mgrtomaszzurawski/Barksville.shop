<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<header>
    <h1>Rejestracja</h1>
</header>
<section>
    <h1>Wypełnij dane rejestracyjne</h1>
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
            <fieldset>
                <legend>Dane użytkownika</legend>
                <p>
                    <label for="name">Nazwa produktu: </label><input id="name"
                                                                            type="text"
                                                                            name="name"/>
                </p>

                <p>
                    <label for="price">Cena na fakturze: </label><input id="price" type="text"
                                                             name="price"/>
                </p>

                <p>
                    <label for="quantity">Ilość: </label><input id="quantity" type="text"
                                                                name="quantity"/>
                </p>
                <p>
                    <label for="cost">Suma na fakturze: </label><input id="cost" type="text"
                                                                name="cost"/>
                </p>
                <p>
                    <label for="invoiceNumber">Nr faktury: </label><input id="invoiceNumber" type="text"
                                                                       name="invoiceNumber"/>
                </p>
                <div class="column">
                    <div class="content">
                        <div class="field is-grouped">
                            <div class="control">
                                <button class="button is-success is-link" type="submit"
                                        name="upload">
                                    Dodaj produkt
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column">
                    <div class="content">
                        <div class="field is-grouped">
                            <div class="control">
                                <button class="button is-success is-link" type="submit"
                                        name="delete">
                                    usun produkt trzeba wsadzic go do listy z przesylaniem jaki to obiekt
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column">
                    <div class="content">
                        <div class="field is-grouped">
                            <div class="control">
                                <button class="button is-success is-link" type="submit"
                                        name="save">
                                    Zapisz liste produktów
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="column"></div>
            </fieldset>

        </form>
    </div>
</section>
<footer></footer>
</body>
</html>
