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



//슬라이드 // 레시피더보기쪽
document.addEventListener('DOMContentLoaded', function() {
    const sliders = document.querySelectorAll('.recipe-slider');

    sliders.forEach(function(slider) {
        const leftArrow = slider.querySelector('.left-arrow');
        const rightArrow = slider.querySelector('.right-arrow');
        const recipeCards = slider.querySelector('.recipe-cards');

        let currentIndex = 0;
        const cardWidth = recipeCards.children[0].offsetWidth + 20; // 카드 너비와 여백을 포함한 값
        const totalCards = recipeCards.children.length;
        const visibleCards = Math.floor(slider.offsetWidth / cardWidth); // 한 번에 보이는 카드 수 계산

        function updateSliderPosition() {
            const maxIndex = totalCards - visibleCards; // 카드들의 총 개수에서 보이는 카드 수만큼 뺀 값
            if (currentIndex < 0) currentIndex = 0; // 왼쪽 끝으로 더 이상 갈 수 없음
            if (currentIndex > maxIndex) currentIndex = maxIndex; // 오른쪽 끝으로 더 이상 갈 수 없음
            recipeCards.style.transform = `translateX(-${currentIndex * cardWidth}px)`; // 카드들을 좌우로 이동
        }

        leftArrow.addEventListener('click', function() {
            currentIndex--;
            updateSliderPosition();
        });

        rightArrow.addEventListener('click', function() {
            currentIndex++;
            updateSliderPosition();
        });
    });
});
