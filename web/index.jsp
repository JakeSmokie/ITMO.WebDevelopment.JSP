<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script src="js/fields_checking.js"></script>
    <script src="js/submit.js"></script>

    <script>
        let prevValue = false;

        function updateField(field) {
            if (checkY(field.value) === prevValue) {
                return;
            }

            $(field).toggleClass("is-invalid");
            $(field).toggleClass("is-valid");

            prevValue = !prevValue;
        }

        $(document).ready(function () {
            for (let x = -2; x <= 2; x += 0.5) {
                $("#x").append('<option>' + x + '</option>');
            }

            for (let r = 1; r <= 5; r += 1) {
                $("#r").append('<option>' + r + '</option>');
            }
        });
    </script>

    <title>$Title$</title>
</head>
<body class="container body-content">
<div style="margin-bottom: 10px"></div>
<h4 style="text-align: center">Айгузин Иван Олегович | P3218 | Вариант 1802</h4>
<hr/>

<div class="row">
    <div class="col-sm">
        <form action="control" method="get" onsubmit="return false;" id="form">
            <div class="row">
                <div class="form-group col-sm" style="text-align: center">
                    <label>X</label>
                    <select class="form-control" id="x" name="x">
                    </select>
                </div>
                <div class="form-group col-sm" style="text-align: center">
                    <label>Y</label>
                    <input type="text" maxlength="10" class="form-control is-invalid" id="y" name="y"
                           placeholder="(-5, 3)"
                           oninput="updateField(this)">
                </div>
                <div class="form-group col-sm" style="text-align: center">
                    <label>R</label>
                    <select class="form-control" id="r" name="r">
                    </select>
                </div>
            </div>
            <div style="text-align: center">
                <a href="javascript:submit();" class="btn btn-primary mb-2 align-bottom">Проверить точку</a>
            </div>
            <hr/>
        </form>
        <div class="container-fluid" id="results" style="text-align: center">

        </div>
    </div>
    <div class="col-sm">
    </div>
</div>

</body>
</html>
