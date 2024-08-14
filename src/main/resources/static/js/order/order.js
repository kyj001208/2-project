$(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content") || 'X-CSRF-TOKEN'; // 기본값 설정

    $(document).ajaxSend(function(e, xhr, options) {
        if (token && header) {
            xhr.setRequestHeader(header, token);
        }
    });
});

function updateOrderStatus(orderNo, newStatus) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content") || 'X-CSRF-TOKEN'; // 기본값 설정

    fetch(`/admin/order`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [header]: token // CSRF 토큰 추가
        },
        body: JSON.stringify({
            orderNo: orderNo,
            status: newStatus
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Order status updated:', data);
        alert('주문 상태가 성공적으로 변경되었습니다.');
    })
    .catch(error => {
        console.error('Error updating order status:', error);
        alert('주문 상태 변경에 실패했습니다.');
    });
}
