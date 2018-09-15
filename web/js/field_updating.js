let prevValue = false;

$(document).ready(async () => {
    updateY();
});

function updateY() {
    const $y = $("#y");

    const check = checkY($y.val());

    if (check === prevValue) {
        return;
    }

    $y.toggleClass("is-invalid");
    $y.toggleClass("is-valid");

    prevValue = !prevValue;
}