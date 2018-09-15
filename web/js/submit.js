$(document).ready(() => {
    $("#checkallbutton").unbind('click').click(() => {
        checkAllChart();
    });
    $("#submitbutton").unbind('click').click(() => {
        submit();
    });
});

function submit() {
    if (!checkFields(document.forms[0])) {
        return;
    }

    const x = Number($("#x").val());
    const y = Number($("#y").val());

    addPoint(x, y);
}

async function checkAllChart() {
    $('#checkallbutton').addClass('disabled');

    const coords = Array.from(generateCoordinates())
        .sort(sortByRadius);

    for (let x of coords) {
        await addPoint(x.x, x.y);
    }
}

function* generateCoordinates() {
    for (let x = 6; x >= -6; x -= 0.5) {
        for (let y = 6; y >= -6; y -= 0.5) {
            yield {x, y};
        }
    }
}

function sortByRadius(x, y) {
    const radX = x.x * x.x + x.y * x.y;
    const radY = y.x * y.x + y.y * y.y;

    if (radX === radY) {
        return 0;
    }

    if (radX > radY) {
        return 1;
    }

    return -1;
}

function addColumn($tr, text) {
    $("<td/>", {
        text: text
    }).appendTo($tr);
}

async function addPoint(chart_x, chart_y) {
    const result = await sendRequest(chart_x, chart_y);

    const draw_x = chart_x * 500 / 14 + _size / 2;
    const draw_y = _size / 2 - chart_y * 500 / 14;

    drawGenericPoint(draw_x, draw_y, result.isPointInArea ? '#28A745' : '#DC3545');
}

async function sendRequest(chart_x, chart_y) {
    const r = Number($("#r").val());
    const params = `x=${chart_x}&y=${chart_y}&r=${r}`;
    const result = await $.get("?" + params, "", "json");

    const $tr = $("<tr/>");
    let {x, y} = getCoordinates(result);

    addColumn($tr, result.date);
    addColumn($tr, x);
    addColumn($tr, y);
    addColumn($tr, result.parameters.r);
    addColumn($tr, result.isPointInArea ? "Да" : "Нет");

    $("#results").prepend($tr);
    return result;
}

function getCoordinates(result) {
    let x = Number(result.parameters.x);
    let y = Number(result.parameters.y);

    if (result.parameters.x.toString().indexOf('.') !== -1) {
        x = x.toFixed(4);
    }

    if (result.parameters.y.toString().indexOf('.') !== -1) {
        y = y.toFixed(4);
    }

    return {x, y};
}
