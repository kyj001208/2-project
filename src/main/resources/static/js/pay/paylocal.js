$(document).ready(function() {
	const csrfToken = $("meta[name='_csrf']").attr("content");
	const csrfHeader = $("meta[name='_csrf_header']").attr("content");

	const savedTotalAmount = localStorage.getItem('totalAmount');

	if (savedTotalAmount) {
		$('#totalAmount').text(parseFloat(savedTotalAmount).toLocaleString() + '원');
	}

	// 결제하기 버튼 클릭 이벤트
	$('#paytossbtn').click(function() {
		var recipient = $('#recipient').val();
        var payAddress = $('#sample6_address').val();
        var phone = $('#paynumber').val();

        var productNames = [];
        var productPrices = [];
        var quantities = [];
        var cartNos = [];

        $('.cart_lists').each(function() {
            var cartNo = $(this).find('.cartNo').text().trim();
            var productName = $(this).find('.pay-name').text().trim();
            var productPrice = $(this).find('.pay-price').data('price');
            var count = $(this).find('.pay-count').data('count');
            console.log('count:', count);

            if (cartNo && productName && productPrice) {
                cartNos.push(cartNo);
                productNames.push(productName);
                productPrices.push(productPrice);
                quantities.push(count)
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
				cartNo: cartNos,
                quantity: quantities
			}),
			success: function(response) {
				
				//window.location.href = 'http://localhost:8080/petifr/pay';
				

			},
			error: function(xhr, status, error) {
				// 실패 시의 처리
				
			}
		});
	});
});
