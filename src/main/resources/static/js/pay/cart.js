$(document).ready(function() {
	// CSRF 토큰을 메타 태그에서 가져오기
	const csrfToken = $("meta[name='_csrf']").attr("content");
	const csrfHeader = $("meta[name='_csrf_header']").attr("content");

	// 페이지 로드 시 총 금액 계산
	calculateTotalAmount();
	saveTotalAmount(); // 총 금액을 localStorage에 저장

	function updateQuantity(element, isIncreasing, cartNo) {
		const $li = $(element).closest('.cart_lists');
		const countInput = $li.find('.count');
		const priceElement = $li.find('.cart-price');
		const priceData = priceElement.data('price');
		
		console.log('Data price attribute:', priceData); // 디버깅용


		const pricePerUnit = parseFloat(priceData);

		console.log('Parsed price per unit:', pricePerUnit); // 디버깅용

		if (isNaN(pricePerUnit)) {
			console.error('Invalid price per unit:', priceData);
			return; // Invalid price value, exit the function
		}

		let currentCount = parseInt(countInput.val());

		if (isIncreasing) {
			currentCount++;
		} else if (currentCount > 1) {
			currentCount--;
		}

		countInput.val(currentCount);
		priceElement.text((currentCount * pricePerUnit).toLocaleString() + '원');

		// 서버에 업데이트 요청
		updateDatabase(cartNo, currentCount);

		// 총 금액 업데이트
		calculateTotalAmount();
		saveTotalAmount();
	}


	// 서버에 데이터 업데이트 함수
	function updateDatabase(cartNo, count) {
		$.ajax({
			url: '/petfir/cart/' + cartNo,  // URL은 적절히 수정해 주세요
			type: 'PUT',
			contentType: 'application/json',
			headers: {
				[csrfHeader]: csrfToken  // CSRF 헤더 추가
			},
			data: JSON.stringify({
				cartNo: cartNo,
				count: count
			}),
			success: function(response) {
				console.log('Database updated successfully:', response);
			},
			error: function(xhr, status, error) {
				console.error('Error updating database:', error);
			}
		});
	}

	// 총 금액 계산 함수
	function calculateTotalAmount() {
		let totalAmount = 0;
		$('.cart-price').each(function() {
			const textPrice = $(this).text().trim();
			const price = parseFloat(textPrice.replace(/,/g, '').replace('원', ''));
			if (!isNaN(price)) {
				totalAmount += price;
			}
		});
		$('#totalAmount').text(totalAmount.toLocaleString() + '원');
	}

	// 총 금액을 localStorage에 저장하는 함수
	function saveTotalAmount() {
		const totalAmountText = $('#totalAmount').text().trim();
		localStorage.setItem('totalAmount', totalAmountText.replace(/원$/, '').replace(/,/g, ''));
	}

	// 수량 조정 버튼 클릭 시 이벤트 리스너
	$('.quantity button').click(function() {
		const isIncreasing = $(this).text() === '+';
		const cartNo = $(this).closest('.cart_lists').find('.cartNo').text();
		updateQuantity(this, isIncreasing, cartNo);
	});

	// 삭제 버튼 클릭 시
	window.deletebtnClicked = function(no) {
		Swal.fire({
			title: '삭제 확인',
			text: "해당 상품을 삭제하시겠습니까?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#f0f0f0',
			cancelButtonColor: '#f0f0f0',
			confirmButtonText: '삭제',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				var form = document.getElementById('deleteForm-' + no);
				form.submit();
			}
		});
	};
});
