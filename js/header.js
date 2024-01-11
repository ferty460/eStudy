document.getElementById('toggleSidebar').addEventListener('click', function() {
    var sidebar = document.getElementById('sidebar');
    var content = document.getElementById('content');

    if (sidebar.classList.contains('sidebar-show') && content.classList.contains('sidebar-show__content')) {
        sidebar.classList.remove('sidebar-show');
        content.classList.remove('sidebar-show__content');
    } else {
        sidebar.classList.add('sidebar-show');
        content.classList.add('sidebar-show__content');
    }
});

document.querySelector('#dropdown-button').addEventListener('click', function() {
    var menu = document.querySelector('.dropdown-menu');
    
    if (menu.classList.contains('dropdown-show')) {
        menu.classList.remove('dropdown-show');
    } else {
        menu.classList.add('dropdown-show');
    }
});

document.querySelector('#submenu').addEventListener('click', function(e) {
    e.preventDefault();
    var submenu = this.parentElement.querySelector('.submenu__list');
    if (submenu.style.maxHeight) {
        submenu.style.maxHeight = null;
    } else {
        submenu.style.maxHeight = submenu.scrollHeight + "px";
    } 
});