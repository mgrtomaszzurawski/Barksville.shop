<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="../elements/header.jsp"/>
</head>
<body>
<jsp:include page="../elements/menu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu tworzenia raportu dziennego</h2>
        <table>
            <form method="post" action="/admin/report-create/day">
            <tr>
                <th>Wygeneruj</th>
            </tr>


            <tr>
                <th>
                    <label for="dayReportDate">Wybierz dzień do utworzenia raportu: </label>
                </th>
            </tr>

            <tr>
                <th>
                    <input id="dayReportDate"
                           type="date"
                           name="dayReportDate"/>
                        <button type="submit">
                            Raport dzienny
                        </button>
                    </form>
                </th>

            </tr>

        </table>


    </div>

</div>
</body>
</html>
