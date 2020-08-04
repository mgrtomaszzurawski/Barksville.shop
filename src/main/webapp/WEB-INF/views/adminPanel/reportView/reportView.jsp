<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="../elements/header.jsp"/>
</head>
<body>
<jsp:include page="../elements/menu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu podglądu raportów</h2>
        <table>
            <tr>
                <th>Sprawdz</th>
            </tr>

            <tr>
                <th>  <form method="get" action="/admin/report-view/day-report-list">
                    <button  type="submit" >
                        Raporty dzienne
                    </button>
                </form></th>

            </tr>

            <tr>
                <th> <form method="get" action="/admin/report-view/week-report-list">
                    <button  type="submit" >
                        Raporty tygodniowe
                    </button>
                </form></th>

            </tr>
            <tr>
                <th> <form method="get" action="/admin/report-view/month-report-list">
                    <button  type="submit" >
                        Raporty miesięczne
                    </button>
                </form></th>
            </tr>
            <tr>
                <th> <form method="get" action="/admin/report-view/year-report-list">
                    <button  type="submit" >
                        Raporty roczne
                    </button>
                </form></th>
            </tr>

        </table>




    </div>

</div>
</body>
</html>

