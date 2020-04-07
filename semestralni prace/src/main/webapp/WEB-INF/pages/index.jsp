<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./header/header.jsp"/>
<div class="jumbotron">
    <h1>PPJ 2020</h1>
    <p>Weather Checker 3000</p>
</div>
<div class="container-fluid">
    <h1>Measured values</h1>
    <div class="form-group">
        <label for="exampleFormControlSelect1">Select country</label>
        <select class="form-control" id="exampleFormControlSelect1">
            <c:forEach items="${data.countryCodes}" var="entry">
                <option
                        <c:if test="${entry.key == data.codeSelectedCountry}">selected</c:if>
                        value="${entry.key}"
                >
                        ${entry.value}
                </option>
            </c:forEach>
        </select>
    </div>
    <div id="tableContainer">
        <table class="table">
            <thead>
            <tr>
                <th>City</th>
                <th>Temp</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data.indexCountryDataDTOList}" var="row">
                <tr>
                    <td>${row.cityName}</td>
                    <td>${row.temp}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="application/javascript">
    function printTableBody(tableId, data) {
        var header = " <table class=\"table\">\n" +
            "            <thead>\n" +
            "            <tr>\n" +
            "                <th>City</th>\n" +
            "                <th>Temp</th>\n" +
            "            </tr>\n" +
            "            </thead>\n" +
            "            <tbody>";
        var dataRows = "";
        for(var i = 0; i < data.length; i++) {
            var row = " <tr>\n" +
                "                    <td>" + data[i].cityName + "</td>\n" +
                "                    <td>" + data[i].temp + "</td>\n" +
                "                </tr>";
            dataRows += row;
        }
        var footer = "</tbody>\n" +
            "        </table>";

        document.getElementById(tableId).innerHTML = header + dataRows + footer;
    }


    $("#exampleFormControlSelect1").change(function (event) {
            var countryCode = event.target.value;
            $.post("/api/getData/" + countryCode, function (data, status) {
                printTableBody("tableContainer", data)
            });
        }
    );
</script>
<jsp:include page="footer/footer.jsp"/>