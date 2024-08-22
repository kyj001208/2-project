const urlParams = new URLSearchParams(window.location.search);
const paymentKey = urlParams.get("paymentKey");
const orderId = urlParams.get("orderId");
const amount = urlParams.get("amount");

const paymentKeyElement = document.getElementById("paymentKey");
const orderIdElement = document.getElementById("orderId");
const amountElement = document.getElementById("amount");

paymentKeyElement.textContent = paymentKey;
orderIdElement.textContent = orderId;
amountElement.textContent = `${amount}원`;

// CSRF 토큰과 헤더 이름 읽기
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


const confirmLoadingSection = document.querySelector('.confirm-loading');
const confirmSuccessSection = document.querySelector('.confirm-success');

async function confirmPayment() {
  // TODO: API를 호출해서 서버에게 paymentKey, orderId, amount를 넘겨주세요.
  // 서버에선 해당 데이터를 가지고 승인 API를 호출하면 결제가 완료됩니다.
  // https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%8A%B9%EC%9D%B8
  const response = await fetch('/sandbox-dev/api/v1/payments/confirm', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      [csrfHeader]: csrfToken
    },
    body: JSON.stringify({
      paymentKey,
      orderId,
      amount
    }),
  });

  if (response.ok) {
    confirmLoadingSection.style.display = 'none';
    confirmSuccessSection.style.display = 'flex';
  }
}

const confirmPaymentButton = document.getElementById('confirmPaymentButton');
confirmPaymentButton.addEventListener('click', confirmPayment);


/////////////////////////확인버튼//////////////////////////
document.addEventListener("DOMContentLoaded", function() {
            const confirmPaymentButton = document.getElementById('confirmPaymentButton');
            const successDiv = document.getElementById('successDiv');
            const successBtn = document.getElementById('successBtn');
            
            // 결제 승인 버튼 클릭 이벤트
            confirmPaymentButton.addEventListener('click', function() {
           
                // 결제 승인 후 확인 버튼 표시
                successDiv.style.display = 'block';
            });
            
            // 확인 버튼 클릭 이벤트
            successBtn.addEventListener('click', function() {
                // 부모 창(메인 창)으로 제어를 이동
                if (window.opener) {
                    window.opener.location.href = '/petfir/mypage/orders'; // 메인 페이지 URL로 변경
                    window.close(); // 현재 창 닫기
                }
            });
        });

