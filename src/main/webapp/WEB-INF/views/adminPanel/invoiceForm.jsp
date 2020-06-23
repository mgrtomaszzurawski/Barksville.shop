<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Invoice Form</title>
    <jsp:include page="invoice_header.jsp"/>
</head>
<body>
<jsp:include page="invoice_menu.jsp"/>

<section>
    <h2>Wypełnij dane faktury</h2>
    <div>
        <form method="post" action="/admin/invoice-list/invoice/data">

            <fieldset>
                <legend>Dane Faktury</legend>
                <p>
                    <label for="company">Nazwa firmy: </label><input id="company" type="text"
                                                                          name="company"/>
                </p>
                <p>
                    <label for="invoiceNumber">Nr faktury: </label><input id="invoiceNumber" type="text"
                                                                          name="invoiceNumber"/>
                </p>
                <p>
                    <label for="invoiceDate">Data wystawienia faktury: </label><input id="invoiceDate" type="date"
                                                                          name="invoiceDate" />
                </p>
                <p>
                    <label for="cost">Suma na fakturze: </label><input id="cost" type="number" placeholder="0.00" step="0.01"
                                                                                      name="cost" />
                </p>

                <div class="column">
                    <div class="content">
                            <div class="field is-grouped">
                                <div class="control">
                                    <button class="button is-success is-link" type="submit"
                                            name="upload">
                                        Zapisz
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
