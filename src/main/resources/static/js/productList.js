 function editProduct(productId) {
            alert(productId + "번 상품을 수정합니다.");
            // 상품 수정 로직 구현
        }

        function deleteProduct(productId) {
            const confirmed = confirm(productId + "번 상품을 삭제하시겠습니까?");
            if (confirmed) {
                // 상품 삭제 로직 구현
                alert(productId + "번 상품이 삭제되었습니다.");
            }
        }