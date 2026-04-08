document.getElementById('searchInput').addEventListener('input', function () {
    const search = this.value.toLowerCase();
    const rows = document.querySelectorAll('tbody tr');

    rows.forEach(function (row) {
        const lastName = row.cells[0].textContent.toLowerCase();
        const firstName = row.cells[1].textContent.toLowerCase();

        if (lastName.includes(search) || firstName.includes(search)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});