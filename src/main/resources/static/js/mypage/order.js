 function showOrderDetails(orderNo) {
            // 간단한 주문 데이터 예시
            const orderData = {
                '20230821-001': {
                    productName: '스마트폰',
                    quantity: 1,
                    price: '₩1,000,000',
                    orderDate: '2024-08-20',
                    status: '배송중'
                },
                '20230821-002': {
                    productName: '무선 이어폰',
                    quantity: 2,
                    price: '₩200,000',
                    orderDate: '2024-08-19',
                    status: '배송완료'
                },
                '20230821-003': {
                    productName: '노트북',
                    quantity: 1,
                    price: '₩2,500,000',
                    orderDate: '2024-08-18',
                    status: '주문접수'
                }
            };

            const orderDetails = orderData[orderNo];
            
            if (orderDetails) {
                document.getElementById('order-no').textContent = orderNo;
                document.getElementById('product-name').textContent = orderDetails.productName;
                document.getElementById('quantity').textContent = orderDetails.quantity;
                document.getElementById('price').textContent = orderDetails.price;
                document.getElementById('order-date').textContent = orderDetails.orderDate;
                document.getElementById('status').textContent = orderDetails.status;
                
                document.getElementById('order-details').classList.remove('hidden');
            }
        }

        function hideOrderDetails() {
            document.getElementById('order-details').classList.add('hidden');
        }