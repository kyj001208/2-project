// 페이지가 완전히 로드 된 후 함수 실행 
  window.onload = function() {
            calculateTotalAmount();
            saveTotalAmount(); // 총 금액을 localStorage에 저장
        };

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
       
       
       
///////////////////////////////////////////////////////////////////////////////// 
