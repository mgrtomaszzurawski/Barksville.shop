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
        <h2>Jesteś w panelu tworzenia raportu rocznego</h2>
        <table>
            <form method="post" action="/admin/report-create/year">
                <tr>
                    <th>Wygeneruj</th>
                </tr>


                <tr>
                    <th>
                        <label for="yearReportDate">Wybierz rok do utworzenia raportu: </label>
                    </th>
                </tr>

                <tr>
                    <th>
                        <input id="yearReportDate"
                               type="date"
                               name="yearReportDate"/>
                        <button type="submit">
                            Raport roczny
                        </button>
            </form>
            </th>

            </tr>

        </table>


    </div>

</div>
</body>
</html>
