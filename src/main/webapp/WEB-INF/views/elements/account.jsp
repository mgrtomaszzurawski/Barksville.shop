<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Twoje konto</title>
    <jsp:include page="header.jsp"/>
</head>
<body class="has-navbar-fixed-top">
<header>
    <jsp:include page="menu.jsp"/>
</header>



            </div>
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
                                <button class="button is-success is-link" type="submit"
                                        name="upload">
                                    Zapisz
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <div class="column"></div>
        </div>
    </div>
</section>
<footer class="footer">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
