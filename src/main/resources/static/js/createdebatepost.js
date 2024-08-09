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

function previewImage(event, previewId) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
        const img = document.getElementById(previewId);
        img.src = e.target.result;
        img.style.display = 'block'; // 이미지 표시
    }

    if (file) {
        reader.readAsDataURL(file);
    }
}
