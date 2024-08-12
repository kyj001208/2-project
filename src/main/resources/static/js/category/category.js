document.addEventListener("DOMContentLoaded", function() {
            // API 엔드포인트에서 카테고리 데이터를 가져옴
            fetch('/api/categories')
                .then(response => response.json())
                .then(data => {
                    populateCategories(data);
                })
                .catch(error => console.error('Error fetching categories:', error));
        });

        function populateCategories(categories) {
            const category1 = document.getElementById('category1');
            const category2 = document.getElementById('category2');
            const category3 = document.getElementById('category3');

            categories.forEach(category => {
                if (category.depth === 1) {
                    const option = document.createElement('option');
                    option.value = category.categoryId;
                    option.textContent = category.categoryName;
                    category1.appendChild(option);
                } else if (category.depth === 2) {
                    const option = document.createElement('option');
                    option.value = category.categoryId;
                    option.textContent = category.categoryName;
                    category2.appendChild(option);
                } else if (category.depth === 3) {
                    const option = document.createElement('option');
                    option.value = category.categoryId;
                    option.textContent = category.categoryName;
                    category3.appendChild(option);
                }
            });
        }