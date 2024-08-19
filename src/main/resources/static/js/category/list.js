
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

// 여기에 loadCategoryAll과 loadCategoryProducts 함수를 추가하세요
function loadCategoryAll() {
    $.get(`/public/categories`, function(data) {
        $("#category-menu").html(data);
    });
}

function loadCategoryProducts(categoryNo) {
    $.get(`/public/categories/${categoryNo}/products`, function(data) {
        $("#product-content").html(data);
    });
}
/*
function createTag(dto) {
    let childCategoriesTag = '';
    
    // dto.depth가 3이 아닌 경우에만 childCategoriesTag를 포함
    if (dto.depth < 3) {
        childCategoriesTag = `<ul id="child-categories-${dto.categoryNo}" class="child-categories" ></ul>`;
    }

    return `<li>
                <a  href="/public/categories/${dto.categoryNo}">${dto.categoryName}</a>
                ${childCategoriesTag}
            </li>`;
}



function loadParentCategories(categoryNo, depth) {
	
	
    $.get("/public/categories/parent", function(data) {
        let parentList = $("#parent-categories");
        parentList.empty();
        data.forEach(function(category) {
        	parentList.append(createTag(category));
        	//하위카테고리 갖고오기
        	loadChildCategories(category)
        });
    });
    
}

function loadChildCategories(categoryNo) {
	loadCategoryProducts(categoryNo);
	
    let childList = 
    $.get(`/public/categories/${categoryNo}/children`, function(data) {
        data.forEach(function(category) {
            childList.append(createTag(category));
        });
    });
}
//*/