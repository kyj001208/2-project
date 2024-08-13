document.addEventListener('DOMContentLoaded', () => {
const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


    const category1 = document.getElementById('category1');
    const category2 = document.getElementById('category2');
    const category3 = document.getElementById('category3');



    category1.addEventListener('change', (event) => {
        // 대분류가 변경될 때 중분류를 로드합니다.
        loadCategories(category1.value, 2, category2); // depth는 1로 설정 (중분류)
    });

    category2.addEventListener('change', (event) => {
        // 중분류가 변경될 때 소분류를 로드합니다.
        loadCategories(category2.value, 3, category3); // depth는 2로 설정 (소분류)
    });

    // 처음 페이지 로드 시 대분류를 로드
    loadCategories(null, 1, category1); // depth는 0으로 설정 (대분류)
});

function loadCategories(parentNo, depth, selectElement) {
    if (!selectElement) {
        console.error(`Select element not found for depth ${depth}.`);
        return;
    }

    // API 호출 URL 구성
    const apiUrl = `/api/categories?depth=${depth}${parentNo !== null && parentNo !== '' ? `&parentNo=${parentNo}` : ''}`;

    // fetch로 API 호출
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok. Status: ${response.status}`);
            }
            return response.json(); // 응답을 JSON으로 변환
        })
        .then(data => {
            console.log('Fetched categories:', data); // 디버깅을 위한 로그 추가
            updateSelectOptions(selectElement, data); // 옵션 업데이트
        })
        .catch(error => {
            console.error('Error fetching categories:', error);
            alert('카테고리를 불러오는 중 오류가 발생했습니다. 다시 시도해주세요.');
        });
}

function updateSelectOptions(selectElement, categories) {
    // 기존 옵션 제거 및 기본값 추가
    selectElement.innerHTML = '<option value="">선택하세요</option>';

    if (categories.length === 0) {
        const option = document.createElement("option");
        option.value = "";
        option.textContent = "해당 분류 없음";
        selectElement.appendChild(option);
        return;
    }

    // 카테고리 추가
    categories.forEach(category => {
        const option = document.createElement("option");
        option.value = category.categoryNo;  // `categoryNo` 사용
        option.textContent = category.categoryName;  // 카테고리 이름 사용
        selectElement.appendChild(option);
    });
}
