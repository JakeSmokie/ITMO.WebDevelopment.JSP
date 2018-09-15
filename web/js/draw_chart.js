function drawChart() {
    drawBackground();
    drawAxis();
    drawArrows();
    drawAxisNames();
    drawAxisValues();
    drawFigure();
}

function drawBackground() {
    _chart.drawRect({
        fillStyle: 'white',
        x: 0, y: 0,
        fromCenter: false,
        width: _size,
        height: _size
    });
}

function drawAxis() {
    drawGenericLine(_size / 20, _size / 2, _size * 19 / 20, _size / 2, 'black');
    drawGenericLine(_size / 2, _size / 20, _size / 2, _size * 19 / 20, 'black');
}

function drawArrows() {
    _chart.drawLine({
        strokeStyle: 'black',
        strokeWidth: 1,
        x1: _size * 24 / 50, y1: _size / 10,
        x2: _size / 2, y2: _size / 20,
        x3: _size * 26 / 50, y3: _size / 10,
    });

    _chart.drawLine({
        strokeStyle: 'black',
        strokeWidth: 1,
        x1: _size * 9 / 10, y1: _size * 24 / 50,
        x2: _size * 19 / 20, y2: _size / 2,
        x3: _size * 9 / 10, y3: _size * 26 / 50,
    });
}

function drawAxisNames() {
    drawGenericText('y', _size * 11 / 20, _size / 20, 25);
    drawGenericText('x', _size * 19 / 20, _size * 9 / 20, 25);
}

function drawAxisValues() {
    for (let i = -5; i <= 5; i++) {
        if (i === 0) {
            continue;
        }

        const x1 = _size / 2 + _size / 14 * i;
        const y1 = _size * 49 / 100;
        const y2 = _size * 51 / 100;

        drawGenericLine(x1, y1, x1, y2, 'black');
        drawGenericLine(y1, x1, y2, x1, 'black');

        drawGenericText(i, x1, y1 - _size / 40, 15);
        drawGenericText(i, y1 + _size / 20, _size / 2 - _size / 14 * i, 15);
    }
}

function drawFigure() {
    const radius = _size / 14 * _radius;
    drawGenericLine(_size / 2, _size / 2 - radius, _size / 2 + radius, _size / 2, '#007bff');
    drawGenericLine(_size / 2, _size / 2 - radius, _size / 2 - radius, _size / 2 - radius, '#007bff');
    drawGenericLine(_size / 2 - radius, _size / 2 - radius, _size / 2 - radius, _size / 2, '#007bff');
    drawGenericLine(_size / 2, _size / 2, _size / 2 - radius, _size / 2, '#007bff');
    drawGenericLine(_size / 2, _size / 2 + radius / 2, _size / 2, _size / 2, '#007bff');
    drawGenericLine(_size / 2 + radius / 2, _size / 2, _size / 2 + radius, _size / 2, '#007bff');

    $('canvas').drawArc({
        strokeStyle: '#007bff',
        strokeWidth: 1,
        x: _size / 2, y: _size / 2,
        radius: radius / 2,
        start: 90, end: 180
    });
}

function drawGenericText(text, x, y, size) {
    _chart.drawText({
        text: text,
        fontFamily: 'helvetica',
        fontSize: size,
        fillStyle: 'lightgray',
        x: x, y: y
    });
}

function drawGenericLine(x1, y1, x2, y2, strokeStyle) {
    _chart.drawLine({
        strokeStyle: strokeStyle,
        strokeWidth: 1,
        x1: x1, y1: y1,
        x2: x2, y2: y2,
    });
}

function drawGenericPoint(draw_x, draw_y, fillStyle) {
    _chart.drawEllipse({
        fillStyle: fillStyle,
        x: draw_x, y: draw_y,
        width: 4, height: 4
    });
}
