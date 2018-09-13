function checkFields(form) {
    return checkY(form.y.value);
}

function checkY(value) {
    const number = Number(value);
    return value.trim() !== "" && !isNaN(number) && number > -5 && number < 3;
}