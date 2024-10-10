const ibaBtn = document.getElementById('iba-recipe-btn');
const proBtn = document.getElementById('pro-recipe-btn');
const ibaContent = document.getElementById('iba-recipe');
const proContent = document.getElementById('pro-recipe');

// 페이지가 로드될 때 IBA 레시피를 기본적으로 보여줌
document.addEventListener('DOMContentLoaded', () => {
    ibaBtn.classList.add('active');  // IBA 버튼에 active 클래스 추가
    ibaContent.style.display = 'block';  // IBA 레시피 보이기
    proContent.style.display = 'none';  // 조주기능사 레시피 숨기기
});

// IBA 버튼 클릭 시
ibaBtn.addEventListener('click', () => {
    ibaBtn.classList.add('active');
    proBtn.classList.remove('active');  // 조주기능사 버튼에서 active 클래스 제거
    ibaContent.style.display = 'block';
    proContent.style.display = 'none';
});

// 조주기능사 버튼 클릭 시
proBtn.addEventListener('click', () => {
    proBtn.classList.add('active');  // 조주기능사 버튼에 active 클래스 추가
    ibaBtn.classList.remove('active');  // IBA 버튼에서 active 클래스 제거
    proContent.style.display = 'block';
    ibaContent.style.display = 'none';
});
