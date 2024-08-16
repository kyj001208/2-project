document.addEventListener('DOMContentLoaded', function() {
    const cartButton = document.querySelector('.product-detail__cart-button');
    
    // cartButton이 null이 아닌지 확인
    if (cartButton) {
        cartButton.addEventListener('click', function() {
            // 상품 ID를 data-* 속성에서 가져옵니다.
            //const productNo = document.querySelector('.product-detail').getAttribute('data-product-id');
    
             // 상품명을 가져옵니다 (예: h2 태그에서 가져오는 경우)
            const productName = document.querySelector('.product-detail__name').textContent.trim();
    
            // 총 수량을 가져옵니다.
            const count = document.querySelector('#count_tit .num').textContent.trim();
            
            // 총 금액을 가져옵니다.
            const price = document.querySelector('#totalPrice strong').textContent.trim();
            // CSRF 토큰과 헤더 이름을 가져옵니다. (예를 들어, 서버에서 템플릿이나 메타 태그로 전달된다고 가정)
            
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
			const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    
            // 서버로 데이터 전송
            fetch('/petfir/saveDoc', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify({
                    //productNo: Number(productNo),
                    productName: productName,  // 상품명 추가
                    count: count,   //총 수량
                    price: price  //총 금액
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('상품이 장바구니에 추가되었습니다.');
                } else {
                    alert('장바구니 추가에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('장바구니 추가 중 오류가 발생했습니다.');
            });
        });
    } else {
        console.error('Cart button element not found');
    }
});
