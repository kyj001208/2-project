document.addEventListener('DOMContentLoaded', function() {
    const cartButton = document.querySelector('.product-detail__cart-button');
    
    // cartButton이 null이 아닌지 확인
    if (cartButton) {
        cartButton.addEventListener('click', function() {
            // 상품 ID를 data-* 속성에서 가져옵니다.
            const productNo = document.querySelector('.product-detail').getAttribute('data-product-id');
            
            // 총 수량을 가져옵니다.
            const count = document.querySelector('#count_tit .num').textContent.trim();
            
            // CSRF 토큰과 헤더 이름을 가져옵니다.
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    
            // jQuery를 사용해 Ajax로 서버로 데이터 전송
            $.ajax({
                url: '/petfir/saveDoc',
                type: 'POST',
                contentType: 'application/json',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                data: JSON.stringify({
                    productNo: productNo,
                    count: count
                }),
                success: function(data) {
                    if (JSON.parse(data).success) {
                        alert('상품이 장바구니에 추가되었습니다.');
                    } else {
                        alert('장바구니 추가에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    alert('장바구니 추가 중 오류가 발생했습니다.');
                }
            });
        });
    } else {
        console.error('Cart button element not found');
    }
});
