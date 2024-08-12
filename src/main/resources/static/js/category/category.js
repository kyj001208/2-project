document.addEventListener("DOMContentLoaded", function () {
    loadCategories(1, 0, document.getElementById("category1")); // 대분류 로드

    document.getElementById("category1").addEventListener("change", function () {
        const parentNo = this.value; // 대분류의 ID
        loadCategories(2, parentNo, document.getElementById("category2")); // 중분류 로드
        clearSubCategories("category3"); // 소분류 초기화
    });

    document.getElementById("category2").addEventListener("change", function () {
        const parentNo = this.value; // 중분류의 ID
        loadCategories(3, parentNo, document.getElementById("category3")); // 소분류 로드
    });
});

// 카테고리 로드 함수
function loadCategories(depth, parentNo, selectElement) {
    fetch(`/api/categories?depth=${depth}&parentNo=${parentNo}`) // API 호출 시 parentNo 포함
        .then(response => response.json())
        .then(data => {
            updateSelectOptions(selectElement, data);
        })
        .catch(error => {
            console.error('Error loading categories:', error);
        });
}

// 선택된 카테고리 옵션 업데이트 함수
function updateSelectOptions(selectElement, categories) {
    if (!selectElement) {
        console.error('Select element is undefined'); // 디버깅을 위한 로그 추가
        return; // selectElement가 undefined일 경우 함수 종료
    }

    // 기존 옵션 제거
    selectElement.innerHTML = '<option value="">선택하세요</option>';

    // 카테고리 추가
    categories.forEach(category => {
        const option = document.createElement("option");
        option.value = category.id; // 카테고리 번호
        option.textContent = category.name; // 카테고리 이름
        selectElement.appendChild(option);
    });
}

// 소분류 초기화
function clearSubCategories(selectId) {
    const selectElement = document.getElementById(selectId);
    if (selectElement) { // selectElement가 정의되어 있는지 확인
        selectElement.innerHTML = '<option value="">선택하세요</option>'; // 소분류 초기화
    }
}
