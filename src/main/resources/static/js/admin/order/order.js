document.addEventListener('DOMContentLoaded', function() {
    const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    window.updateOrderStatus = function(selectElement) {
        const orderNo = selectElement.getAttribute('data-order-no');
        const status = selectElement.value;

        // Construct URL with parameters
        const params = new URLSearchParams();
        params.append('status', status);

        // Send PUT request
        fetch(`/admin/order/${orderNo}?${params.toString()}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [header]: token // CSRF 토큰 추가
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // 응답이 JSON이 아닌 경우
        })
        .then(data => {
            console.log('Order status updated:', data);
            Swal.fire('처리상태가 성공적으로 변경되었습니다.');
        })
        .catch(error => {
            console.error('Error updating order status:', error);
            Swal.fire('주문 상태 변경에 실패했습니다.');
        });
    };
});
