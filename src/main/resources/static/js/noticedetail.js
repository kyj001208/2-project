$(document).ready(function () {
    $('#summernote').summernote();

    const editButton = document.querySelector("#edit-button");
    editButton.addEventListener('click', modeChange);
    const cancelButton = document.querySelector("#cancel-button");
    cancelButton.addEventListener('click', modeChange);
});

function modeChange() {
    document.querySelector('#cs>.wrap').classList.toggle('edit-mode');
}
