document.addEventListener('DOMContentLoaded', function() {
	const cartButton = document.querySelector('.product-detail__cart-button');

	if (cartButton) {
		// 이전에 장바구니에 담긴 상태를 localStorage에서 확인합니다.
		let isAddedToCart = localStorage.getItem('isAddedToCart') === 'true';

		if (isAddedToCart) {
			// 이미 장바구니에 담겼다면 버튼을 비활성화합니다.
			cartButton.disabled = true;
		}

		cartButton.addEventListener('click', function(event) {
			if (isAddedToCart) {
				// 이미 장바구니에 담긴 경우
				Swal.fire({
					title: "The Internet?",
					text: "That thing is still around?",
					icon: "question"
				});
				event.preventDefault(); // 기본 동작을 막습니다.
			} else {
				// 상품 ID와 수량을 가져옵니다.
				const productNo = document.querySelector('.product-detail').getAttribute('data-product-id');
				const count = document.querySelector('#count_tit .num').textContent.trim();

				// CSRF 토큰과 헤더 이름을 가져옵니다.
				const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
				const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

				// jQuery를 사용해 AJAX로 서버로 데이터 전송
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
							Swal.fire({
								icon: 'success',
								title: '장바구니에 담겼습니다!',
								confirmButtonText: '확인'
							}).then(() => {
								// 장바구니에 추가가 완료된 후 버튼을 비활성화합니다.
								cartButton.disabled = true;
								isAddedToCart = true; // 상태를 업데이트합니다.
								localStorage.setItem('isAddedToCart', 'true'); // 상태를 저장합니다.
							});
						} else {
							Swal.fire({
								icon: 'error',
								title: '장바구니에 추가 실패',
								text: '장바구니에 추가하는 동안 문제가 발생했습니다.',
								confirmButtonText: '확인'
							});
						}
					},
					error: function(xhr, status, error) {
						Swal.fire({
							icon: 'error',
							title: '장바구니 추가 오류',
							text: '장바구니 추가 중 오류가 발생했습니다.',
							confirmButtonText: '확인'
						});
					}
				});
			}
		});
	} else {
		console.error('Cart button element not found');
	}
});
