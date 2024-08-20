/////////////////////////////*토글처리*///////////////
document.addEventListener('DOMContentLoaded', function() {
    // 카테고리 메뉴 컨테이너에 이벤트 리스너 추가
    document.getElementById('category-menu').addEventListener('click', function(e) {
        // 클릭된 요소가 토글 아이콘인지 확인
        if (e.target.classList.contains('toggle-icon')) {
            e.preventDefault();
            
            // 토글 아이콘의 부모 li 요소 찾기
            const parentLi = e.target.closest('li');
            
            // 하위 카테고리 ul 요소 찾기
            const childCategories = parentLi.querySelector('ul.child-categories');
            
            if (childCategories) {
                // 하위 카테고리 토글
                childCategories.classList.toggle('hidden');
                
                // 아이콘 회전
                e.target.classList.toggle('rotated');
            }
        }
    });
});

// 카테고리 메뉴 로딩
function loadCategoryAll() {
    $.get(`/public/categories`, function(data) {
        $("#category-menu").html(data);
    });
}

// 카테고리별 상품 로딩
function loadCategoryProducts(categoryNo) {
    $.get(`/public/categories/${categoryNo}/products`, function(data) {
        $("#product-content").html(data);
    });
}

// 카테고리 태그 생성
function createTag(dto) {
    let childCategoriesTag = '';
    
    // dto.depth가 3이 아닌 경우에만 childCategoriesTag를 포함
    if (dto.depth < 3) {
        childCategoriesTag = `<ul id="child-categories-${dto.categoryNo}" class="child-categories hidden"></ul>`;
    }

    return `<li>
                <a href="/public/categories/${dto.categoryNo}">${dto.categoryName}</a>
                ${childCategoriesTag}
            </li>`;
}

// 상위 카테고리 로딩
function loadParentCategories(categoryNo) {
    $.get("/public/categories/parent", function(data) {
        let parentList = $("#parent-categories");
        parentList.empty();
        data.forEach(function(category) {
            parentList.append(createTag(category));
            // 하위 카테고리 갖고오기
            loadChildCategories(category.categoryNo);
        });
    });
}

// 하위 카테고리 로딩
function loadChildCategories(categoryNo) {
    $.get(`/public/categories/${categoryNo}/children`, function(data) {
        let childList = $(`#child-categories-${categoryNo}`);
        childList.empty();
        data.forEach(function(category) {
            childList.append(createTag(category));
        });
    });
}

// 썸네일 생성 및 로딩
document.addEventListener("DOMContentLoaded", function() {
    const thumbnails = document.querySelectorAll('img.thumbnail');

    thumbnails.forEach(thumbnail => {
        const src = thumbnail.getAttribute('data-src');
        if (src) {
            const img = new Image();
            img.onload = function() {
                const canvas = document.createElement('canvas');
                const ctx = canvas.getContext('2d');
                const maxDimension = 100; // 썸네일의 최대 너비/높이
                
                // 이미지 비율에 맞춰 캔버스 크기 설정
                if (img.width > img.height) {
                    canvas.width = maxDimension;
                    canvas.height = (img.height / img.width) * maxDimension;
                } else {
                    canvas.height = maxDimension;
                    canvas.width = (img.width / img.height) * maxDimension;
                }

                ctx.drawImage(img, 0, 0, canvas.width, canvas.height);

                // 썸네일을 data URL로 변환하고 이미지 src에 설정
                thumbnail.src = canvas.toDataURL('image/jpeg');
            };
            img.src = src;
        }
    });
});
