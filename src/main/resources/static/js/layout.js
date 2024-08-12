document.addEventListener('DOMContentLoaded', function() {
    const categoryToggle = document.querySelector('.category > li:nth-child(2)'); // '카테고리' 항목 선택

    if (categoryToggle) { // 요소가 존재하는지 확인
        categoryToggle.addEventListener('click', function(event) {
            event.stopPropagation(); // 클릭 이벤트 전파 방지
            const subcategoryList = this.querySelector('.subcategory-list');
            if (subcategoryList) { // 서브 카테고리가 존재하는지 확인
                if (subcategoryList.style.display === 'block') {
                    subcategoryList.style.display = 'none'; // 서브 카테고리 숨김
                } else {
                    subcategoryList.style.display = 'block'; // 서브 카테고리 표시
                }
            }
        });
    }

    // 클릭 외부 영역 클릭 시 서브 카테고리 숨김
    document.addEventListener('click', function() {
        if (categoryToggle) { // categoryToggle 요소가 존재하는지 확인
            const subcategoryList = categoryToggle.querySelector('.subcategory-list');
            if (subcategoryList) { // 서브 카테고리가 존재하는지 확인
                subcategoryList.style.display = 'none'; // 서브 카테고리 숨김
            }
        }
    });
});

