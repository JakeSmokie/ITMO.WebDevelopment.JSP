$(document).ready(function () {
    const $x = $("#x");

    for (let x = -2; x <= 2; x += 0.5) {
        $('<option/>', {
            val: x,
            text: x
        }).appendTo($x);
    }

    $('option[value="0"]', $x).attr('selected', 'selected');
});