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
