<%@ page import="ru.jakesmokie.weblab2.servlets.AreaCheckServletResult" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.concurrent.ConcurrentLinkedQueue" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <!-- Local Fallback -->
    <script>window.jQuery || document.write('<script src="../../js/jquery-3.3.1.js"><\/script>')</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jcanvas/21.0.1/jcanvas.js"
            integrity="sha256-ZqFgz7QUEDRCvHiosY/FIao3Zg5Pmg2J4rlLTEWu4BI=" crossorigin="anonymous"></script>

    <script src="js/fields_checking.js"></script>
    <script src="js/submit.js"></script>
    <script src="js/field_updating.js"></script>
    <script src="js/fields_generation.js"></script>
    <script src="js/draw_chart.js"></script>
    <script src="js/chart.js"></script>

    <link rel="stylesheet" href="css/styles.css">
    <link rel="shortcut icon" href="images/skull.ico" sizes="">

    <title>Программирование Интернет-приложений</title>
</head>
<body class="container body-content">
<h4 style="text-align: center">Айгузин Иван Олегович | P3218 | Вариант 1802</h4>
<hr/>
<div class="row">
    <jsp:include page="canvas.jsp"/>
    <div class="col-sm">
        <form action="control" method="get" onsubmit="return false;" id="form">
            <div class="row disable-selection">
                <div class="form-group col-sm" style="text-align: center">
                    <label>X</label>
                    <select class="form-control" id="x" name="x">
                    </select>
                </div>
                <div class="form-group col-sm" style="text-align: center">
                    <label>Y</label>
                    <input type="text" maxlength="10" class="form-control is-invalid" id="y" name="y"
                           placeholder="(-5, 3)"
                           oninput="updateY()">
                </div>
                <div class="form-group col-sm" style="text-align: center">
                    <label>R</label>
                    <select class="form-control" id="r" name="r">
                        <% for (int i = 1; i <= 5; i++) { %>
                        <option <%=i == 5 ? "selected=\"selected\"" : ""%>>
                            <%=i%>
                        </option>
                        <%} %>
                    </select>
                </div>
            </div>
            <div style="text-align: center" class="disable-selection">
                <a href="javascript:void(0)" class="btn btn-primary mb-2 align-bottom" id="submitbutton">
                    Проверить точку</a>
                <a href="javascript:void(0)" class="btn btn-secondary mb-2 align-bottom" id="checkallbutton">
                    Проверить весь график</a>
            </div>
        </form>
        <table class="table table-sm table-hover">
            <thead>
            <tr>
                <th scope="col">Время исчисления</th>
                <th scope="col">X</th>
                <th scope="col">Y</th>
                <th scope="col">R</th>
                <th scope="col">Попадание</th>
            </tr>
            </thead>
            <tbody id="results">
            <%
                if (session == null) {
                    return;
                }

                List<AreaCheckServletResult> history;

                if (session.getAttribute("history") == null) {
                    history = new ArrayList<>();
                } else {
                    history = new ArrayList<>((ConcurrentLinkedQueue<AreaCheckServletResult>)
                            session.getAttribute("history"));
                }

                history.sort(Comparator.comparing(AreaCheckServletResult::getDate).reversed());
            %>
            <% for (final AreaCheckServletResult entry : history) { %>
            <tr>
                <td>
                    <%= entry.getDate() %>
                </td>
                <td>
                    <%= entry.getParameters().getX() %>
                </td>
                <td>
                    <%= entry.getParameters().getY() %>
                </td>
                <td>
                    <%= entry.getParameters().getR() %>
                </td>
                <td>
                    <%= entry.isPointInArea() ? "Да" : "Нет" %>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
