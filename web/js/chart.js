let _size = 0;
let _chart = 0;
let _radius = 0;

$(document).ready(loadChart);

function loadChart() {
    _chart = $("#chart");
    _size = Number(_chart.attr("width").replace("px", ""));

    $("#r").change(() => {
        updateRadiusValue();
        drawChart();
    });

    updateRadiusValue();
    drawChart();

    _chart.click(e => {
        chartClick(e);
    });

    $('#checkallbutton').mousedown(() => false);
    $('#submitbutton').mousedown(() => false);
}

function updateRadiusValue() {
    _radius = Number($("#r").val());
}

$.getScript('js/submit.js');

function chartClick(e) {
    const x = e.pageX - _chart.offset().left;
    const y = e.pageY - _chart.offset().top;

    const chart_x = (x - _size / 2) * 14 / 500;
    const chart_y = (_size / 2 - y) * 14 / 500;

    addPoint(chart_x, chart_y);
}
