$(document).ready(function() {
	const csrfToken = $("meta[name='_csrf']").attr("content");
	const csrfHeader = $("meta[name='_csrf_header']").attr("content");

	const savedTotalAmount = localStorage.getItem('totalAmount');

	if (savedTotalAmount) {
		$('#totalAmount').text(parseFloat(savedTotalAmount).toLocaleString() + '원');
	}

	// 결제하기 버튼 클릭 이벤트
	$('.paytossbtn').click(function() {
		var recipient = $('#recipient').val(); // 수정: '#' 추가
		var payAddress = $('#sample6_address').val(); // 수정: '#' 추가
		var phone = $('#paynumber').val(); // 수정: '#' 추가

		// 상품 정보 수집
		var productNames = [];
		var productPrices = [];
		var cartNos = []; // 장바구니 번호를 담는 배열

		$('.cart_lists').each(function() {
			var cartNo = $(this).find('.cartNo').text().trim();
			var productName = $(this).find('.pay-name').text().trim();
			var productPrice = $(this).find('.pay-price').data('price');
			console.log('Product Price:', productPrice);

			// 장바구니 번호와 상품명, 가격이 유효한 경우에만 추가
			if (cartNo && productName && productPrice) {
				cartNos.push(cartNo); // cartNos 배열에 추가
				productNames.push(productName); // productNames 배열에 추가
				productPrices.push(productPrice); // productPrices 배열에 추가
			}
		});

		// 데이터 전송
		$.ajax({
			url: '/petfir/paySaveDoc', // 서버에서 받을 엔드포인트
			method: 'POST',
			contentType: 'application/json',
			headers: {
				[csrfHeader]: csrfToken  // CSRF 헤더 추가
			},
			data: JSON.stringify({
				recipient: recipient,
				payAddress: payAddress,
				phone: phone,
				productName: productNames, // 수정된 변수 이름
				productPrice: productPrices, // 수정된 변수 이름
				cartNo: cartNos // 수정된 변수 이름
				//status: '대기중' // 결제 상태를 대기중으로 설정
			}),
			success: function(response) {
				// 성공 시의 처리
				

			},
			error: function(xhr, status, error) {
				// 실패 시의 처리
				
			}
		});
	});
});
