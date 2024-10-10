function showUsers() {
    // 사용자 리스트 보이기
    document.getElementById('user-list').style.display = 'grid';
    // 레시피 리스트 숨기기
    document.getElementById('recipe-list').style.display = 'none';

    // 버튼 스타일 업데이트
    document.getElementById('user-btn').classList.add('active');
    document.getElementById('recipe-btn').classList.remove('active');
}

function showRecipes() {
    // 레시피 리스트 보이기
    document.getElementById('recipe-list').style.display = 'grid';
    // 사용자 리스트 숨기기
    document.getElementById('user-list').style.display = 'none';

    // 버튼 스타일 업데이트
    document.getElementById('recipe-btn').classList.add('active');
    document.getElementById('user-btn').classList.remove('active');
}
