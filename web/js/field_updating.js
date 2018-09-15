let prevValue = false;

function updateField(field) {
    const check = checkY(field.value);

    if (check === prevValue) {
        return;
    }

    $(field).toggleClass("is-invalid");
    $(field).toggleClass("is-valid");

    prevValue = !prevValue;
}