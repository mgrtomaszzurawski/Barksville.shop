<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Invoice scan upload</title>
    <jsp:include page="invoiceHeader.jsp"/>
</head>
<body>
<jsp:include page="invoiceMenu.jsp"/>

    <tr>
        <td>1</td>
        <td><b>${scan.fileName}</b></td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}">
                <button type="submit" name="delete">>usun</button>
            </form>
        </td>
    </tr>


<section>
    <h2>Wypełnij dane rejestracyjne</h2>
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

                <legend>Dodaj skan faktury</legend>

                <div class="column">
                    <div class="content">
                        <form enctype="multipart/form-data" method="post">
                            <div class="field">
                                <div class="label" for="file">Faktura</div>
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
                                        Prześlij wskazany plik
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="column"></div>

                <form method="post" action="${pageContext.request.contextPath}">
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
