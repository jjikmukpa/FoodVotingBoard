function previewImage(event, previewId) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
        const img = document.getElementById(previewId);
        img.src = e.target.result;
        img.style.display = 'block'; // 이미지 표시
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}

