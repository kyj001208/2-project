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
        const totalPrice = parseFloat(priceElement.text().replace(/,/g, '').replace('원', '')); // 현재 총 가격
        const currentCount = parseInt(countInput.val()); // 현재 수량

        // 단가 계산 (총 가격 / 수량)
        const pricePerUnit = totalPrice / currentCount;

        // 수량 증가 또는 감소 처리
        if (isIncreasing) {
            countInput.val(currentCount + 1);
        } else if (currentCount > 1) {
            countInput.val(currentCount - 1);
        }

        // 새로운 총 가격 계산
        const newCount = parseInt(countInput.val());
        const totalprice = pricePerUnit * newCount;
        priceElement.text(totalprice.toLocaleString() + '원');

        // 서버에 업데이트 요청
        updateDatabase(cartNo, newCount, totalprice); // newTotalPrice 추가

        // 총 금액 업데이트
        calculateTotalAmount();
        saveTotalAmount();
    }

    // 서버에 데이터 업데이트 함수
    function updateDatabase(cartNo, count, totalprice) {
        $.ajax({
            url: '/petfir/cart/' + cartNo,
            type: 'PUT',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken  // CSRF 헤더 추가
            },
            data: JSON.stringify({
                cartNo: cartNo,
                count: count,
                totalprice:totalprice,

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
        const prices = document.querySelectorAll('.cart-price');

        prices.forEach(function(priceElement) {
            const textPrice = priceElement.textContent.trim();
            const price = parseFloat(textPrice.replace(/,/g, ''));
            if (!isNaN(price)) {
                totalAmount += price;
            }
        });

        document.getElementById('totalAmount').innerText = totalAmount.toLocaleString() + '원';
    }

    function saveTotalAmount() {
        const totalAmountText = document.getElementById('totalAmount').textContent.trim();
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
























/*
$(document).ready(function() {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");

    const savedTotalAmount = localStorage.getItem('totalAmount');

    if (savedTotalAmount) {
        $('#totalAmount').text(parseFloat(savedTotalAmount).toLocaleString() + '원');
    }

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
            url: '/petfir/paySaveDoc',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: JSON.stringify({
                recipient: recipient,
                payAddress: payAddress,
                phone: phone,
                productName: productNames,
                productPrice: productPrices,
                cartNo: cartNos,
                quantity: quantities
            }),
            success: function(response) {
                // 장바구니 초기화 요청
                $.ajax({
                    url: '/api/cart/clear',
                    method: 'POST',
                    headers: {
                        [csrfHeader]: csrfToken
                    },
                    data: {
                       marketNo:marketNo// 사용자 ID를 여기에 설정 
                    },
                    success: function() {
                        alert('결제 완료 및 장바구니가 초기화되었습니다.');
                        // 결제 페이지를 새로 고치거나 장바구니 페이지로 리다이렉션
                        location.href = '/petfir/cart'; // 장바구니 페이지로 리다이렉션
                    },
                    error: function(xhr, status, error) {
                        alert('장바구니 초기화 중 오류가 발생했습니다.');
                    }
                });
            },
            error: function(xhr, status, error) {
                alert('결제 처리 중 오류가 발생했습니다.');
            }
        });
    });
});

*/
