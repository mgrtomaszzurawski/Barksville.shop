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
        <h2>Jesteś w panelu tworzenia raportu tygodniowego</h2>
        <table>
            <form method="post" action="/admin/report-create/week">
                <tr>
                    <th>Wygeneruj</th>
                </tr>


                <tr>
                    <th>
                        <label for="weekReportDate">Wybierz tydzień do utworzenia raportu: </label>
                    </th>
                </tr>

                <tr>
                    <th>
                        <input id="weekReportDate"
                               type="date"
                               name="weekReportDate"/>
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
