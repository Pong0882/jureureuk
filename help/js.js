const top10Items = [
    '1 모스코 뮬',
    '2 화이트 러시안',
    '3 피치 드림',
    '4 블러디 메리',
    '5 마가리타',
    '6 마티니',
    '7 진토닉',
    '8 블루 라군',
    '9 핑크 레이디',
    '10 롱 아일랜드 아이스티'
];

let currentIndex = 0;

function rotateTop10() {
    // 기존 텍스트에 사라지는 애니메이션 적용
    const top10Container = document.getElementById('top10-container');
    const oldItem = document.getElementById('top10-item');
    if (oldItem) {
        oldItem.style.animation = 'slide-out 1s ease-in-out forwards'; // 사라지는 애니메이션
        setTimeout(() => {
            top10Container.removeChild(oldItem);
        }, 1000);  // 사라지는 애니메이션 끝난 후 삭제
    }

    // 새 텍스트 요소 생성
    const newItem = document.createElement('p');
    newItem.id = 'top10-item';
    newItem.textContent = top10Items[currentIndex];
    newItem.style.animation = 'slide-in 1s ease-in-out forwards'; // 올라오는 애니메이션

    // 컨테이너에 새 요소 추가
    setTimeout(() => {
        top10Container.appendChild(newItem);
    }, 1000); // 이전 텍스트가 사라진 후에 새로운 텍스트가 올라옴

    // 인덱스 업데이트
    currentIndex = (currentIndex + 1) % top10Items.length;
}

// 2초마다 텍스트 변경
setInterval(rotateTop10, 2000);
