<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="reportCreateHeader.jsp"/>
</head>
<body>
<jsp:include page="reportCreateMenu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu tworzenia raportu miesięcznego</h2>
        <table>
            <form method="post" action="/admin/report-create/month">
                <tr>
                    <th>Wygeneruj</th>
                </tr>


                <tr>
                    <th>
                        <label for="monthReportDate">Wybierz miesiąc do utworzenia raportu: </label>
                    </th>
                </tr>

                <tr>
                    <th>
                        <input id="monthReportDate"
                               type="month"
                               name="monthReportDate"/>
                        <button type="submit">
                            Raport miesięczny
                        </button>
            </form>
            </th>

            </tr>

        </table>


    </div>

</div>
</body>
</html>
