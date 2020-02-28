<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>

<c:forEach items="${scans}" var="scan" varStatus="stat">
    <tr>
        <td>${stat.count}</td>
        <td><b>${scan.fileName}.${scan.contentType}</b></td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}">
                <input type="hidden" name="name" value="${scan.fileName}"/>
                <button type="submit" name="delete">>usun</button>
            </form>
        </td>
    </tr>
</c:forEach>
<header>
    <h1>Rejestracja</h1>
</header>
<section>
    <h1>Wypełnij dane rejestracyjne</h1>
    <div>

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

                <legend>Dane użytkownika</legend>

                <div class="column">
                    <div class="content">
                        <form enctype="multipart/form-data" method="post">
                            <div class="field">
                                <div class="label" for="file">report</div>
                                <div class="file has-name">
                                    <label class="file-label">
                                        <input class="file-input" type="file" name="file" id="file" accept=".pdf">
                                        <span class="file-cta">
                                      <span class="file-icon">
                                        <i class="fas fa-upload"></i>
                                      </span>
                                      <span class="file-label">
                                        Wybierz plik raportu
                                      </span>
                                    </span>
                                        <span class="file-name">
                                      ---
                                    </span>
                                    </label>
                                </div>

                                <script>
                                    var fileInput = document.querySelector('#file');
                                    fileInput.onchange = function () {
                                        if (fileInput.files.length > 0) {
                                            var fileName = document.querySelector('.file .file-name');
                                            fileName.textContent = fileInput.files[0].name;
                                        }
                                    }
                                </script>
                            </div>

                            <div class="field is-grouped">
                                <div class="control">
                                    <button  type="submit" name="upload">
                                        Zapisz
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="column"></div>

                <form method="post">
            <div class="column">
                <div class="content">
                    <div class="field is-grouped">
                        <div class="control">
                            <button  type="submit" name="next">
                                zapisz i przejdz dalej
                            </button>
                        </div>
                    </div>

                </div>

            </div>
                    </form>
</section>
<footer></footer>
</body>
</html>
