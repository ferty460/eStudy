function closeForm() {
    document.getElementById('formContainer').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('body').style.overflow = 'auto';
}

document.getElementById('openForm').addEventListener('click', function () {
    document.getElementById('formContainer').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('body').style.overflow = 'hidden';
});

document.getElementById('overlay').addEventListener('click', function (event) {
    var isClickInsideForm = document.getElementById('formContainer').contains(event.target);
    if (!isClickInsideForm) {
        closeForm();
    }
});

document.getElementById('new-note-button').addEventListener('click', function () {
    closeForm();
});