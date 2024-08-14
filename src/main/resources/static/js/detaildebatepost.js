let voteCounts = { image1: 0, image2: 0 };

function voteImage(selectedImage) {
    // 확인 알림창
    const confirmation = confirm("투표하시겠습니까?");
    if (confirmation) {
        // 선택한 이미지에 대한 투표 수 증가

        voteCounts[selectedImage]++;
        // 투표 완료 메시지
        alert("투표가 완료되었습니다!");
        // 현재 투표 현황 업데이트
        updateVoteDisplay();
    }
}

function updateVoteDisplay() {
    // 현재 투표 현황을 화면에 업데이트

    document.getElementById('vote-container').innerHTML = `
                <strong>현재 투표 현황:</strong><br>
                이미지 1: ${voteCounts.image1}표<br>
                이미지 2: ${voteCounts.image2}표
            `;
}