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

function confirmTemporarySave(event) {
    event.preventDefault(); // 기본 폼 제출 방지
    const confirmation = confirm("임시 저장하시겠습니까?");
    if (confirmation) {
        saveTemporarily();
    }
}


function saveTemporarily() {
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    // 로컬 스토리지에 데이터 저장
    localStorage.setItem('tempTitle', title);
    localStorage.setItem('tempContent', content);

    // 이미지 파일 처리
    const file1 = document.getElementById('file1').files[0];
    const file2 = document.getElementById('file2').files[0];

    if (file1) {
        const reader1 = new FileReader();
        reader1.onload = function (event) {
            localStorage.setItem('tempImage1', event.target.result);
            document.getElementById('preview1').src = event.target.result; // 미리보기
        };
        reader1.readAsDataURL(file1);
    }

    if (file2) {
        const reader2 = new FileReader();
        reader2.onload = function (event) {
            localStorage.setItem('tempImage2', event.target.result);
            document.getElementById('preview2').src = event.target.result; // 미리보기
        };
        reader2.readAsDataURL(file2);
    }

    alert("임시 저장이 완료되었습니다.");
}


window.onload = function() {
    const savedTitle = localStorage.getItem('tempTitle');
    const savedContent = localStorage.getItem('tempContent');
    const savedImage1 = localStorage.getItem('tempImage1');
    const savedImage2 = localStorage.getItem('tempImage2');

    if (savedTitle) {
        document.getElementById('title').value = savedTitle;
    }
    if (savedContent) {
        document.getElementById('content').value = savedContent;
    }
    if (savedImage1) {
        document.getElementById('preview1').src = savedImage1; // 미리보기
        document.getElementById('preview1').style.display = 'block'; // 이미지 표시
    }
    if (savedImage2) {
        document.getElementById('preview2').src = savedImage2; // 미리보기
        document.getElementById('preview2').style.display = 'block'; // 이미지 표시
    }
};
