async function submit() {
    if (!checkFields(document.forms[0])) {
        return;
    }

    const params = $("#form").serialize();
    const result = await $.get("control?" + params, "", "json");

    printResult(result);
}