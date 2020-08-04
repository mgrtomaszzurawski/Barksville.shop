<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Strona użytkownika</title>
    <jsp:include page="reportCreateHeader.jsp"/>
</head>
<body>
<jsp:include page="reportCreateMenu.jsp"/>
<div class="container">
    <div class="row center">
        <h2>Jesteś w panelu tworzenia raportów</h2>
        <table>
            <tr>
                <th>Wygeneruj</th>
            </tr>

            <tr>
                <th>  <form method="get" action="/admin/report-create/day">
                    <button  type="submit" >
                        Raport dzienny
                    </button>
                </form></th>

            </tr>

            <tr>
                <th> <form method="get" action="/admin/report-create/week">
                    <button  type="submit" >
                        Raport tygodniowy
                    </button>
                </form></th>

            </tr>
            <tr>
                <th> <form method="get" action="/admin/report-create/month">
                    <button  type="submit" >
                        Raport miesięczny
                    </button>
                </form></th>
            </tr>
            <tr>
                <th> <form method="get" action="/admin/report-create/year">
                    <button  type="submit" >
                        Raport roczny
                    </button>
                </form></th>
            </tr>

        </table>




    </div>

</div>
</body>
</html>

