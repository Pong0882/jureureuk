// /UI/js/top10.js

// API에서 상위 10개 칵테일 데이터를 가져옵니다.
fetch('/api/cocktails/top10')
    .then(response => {
        if (!response.ok) {
            throw new Error('API 요청 실패');
        }
        return response.json();
    })
    .then(data => {
        startTop10Rotation(data); // 순환 애니메이션 시작
    })
    .catch(error => console.error('Error fetching top 10 cocktails:', error));

// 상위 10개 칵테일을 2초 간격으로 순환하며 표시하고, 클릭 시 상세 페이지로 이동하는 함수
function startTop10Rotation(cocktails) {
    const top10Item = document.getElementById('top10-item'); // 텍스트 영역
    let currentIndex = 0; // 현재 표시 중인 칵테일 인덱스

    // 첫 번째 칵테일을 즉시 표시
    top10Item.textContent = `1위 - ${cocktails[currentIndex].name}`;
    top10Item.classList.add('show'); // 애니메이션 적용

    top10Item.onclick = () => {
        window.location.href = `/cocktail/${cocktails[currentIndex].id}`;
    };

    // 2초마다 다음 칵테일로 전환
    setInterval(() => {
        // 현재 텍스트를 숨김 처리 (위로 사라짐)
        top10Item.classList.remove('show');
        top10Item.classList.add('hide');

        setTimeout(() => {
            // 다음 칵테일 이름과 ID 설정
            currentIndex = (currentIndex + 1) % cocktails.length;
            top10Item.textContent = `${currentIndex + 1}위 - ${cocktails[currentIndex].name}`;

            // 클릭 이벤트 업데이트
            top10Item.onclick = () => {
                window.location.href = `/cocktail/${cocktails[currentIndex].id}`;
            };

            // 새 텍스트 표시 (아래에서 등장)
            top10Item.classList.remove('hide');
            top10Item.classList.add('show');
        }, 600); // 0.6초 대기
    }, 3000);
}
