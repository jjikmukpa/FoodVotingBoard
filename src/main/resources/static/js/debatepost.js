document.addEventListener('DOMContentLoaded', function() {
    var menubar = document.getElementById('menubar');
    menubar.addEventListener('click', function() {
        toggleSidebar();
    });

    var closeSidebarBtn = document.getElementById('closeSidebar');
    closeSidebarBtn.addEventListener('click', function() {
        toggleSidebar();
    });

    var menuItems = document.querySelectorAll('#sidebar > ul > li > a');
    menuItems.forEach(function(menuItem) {
        menuItem.addEventListener('click', function(event) {
            var submenu = menuItem.nextElementSibling;
            if (submenu && submenu.classList.contains('submenu')) {
                event.preventDefault();
                if (submenu.style.display === 'block') {
                    submenu.style.display = 'none';
                } else {
                    submenu.style.display = 'block';
                }
            }
        });
    });
});

function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    if (sidebar.style.left === '-250px') {
        sidebar.style.left = '0';
    } else {
        sidebar.style.left = '-250px';
    }
}


function searchPosts() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();
    const table = document.getElementById('postsTable');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        let rowVisible = false;

        for (let j = 0; j < cells.length; j++) {
            if (cells[j].textContent.toLowerCase().indexOf(filter) > -1) {
                rowVisible = true;
                break;
            }
        }

        rows[i].style.display = rowVisible ? '' : 'none';
    }
}

