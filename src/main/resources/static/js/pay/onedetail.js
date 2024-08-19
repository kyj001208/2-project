window.onload = function() {
    saveTotalAmount(); // 총 금액을 localStorage에 저장
};

function saveTotalAmount() {
    const totalAmountText = document.getElementById('totalPrice').textContent.trim();
    localStorage.setItem('totalPrice', totalAmountText.replace(/원$/, '').replace(/,/g, ''));
}
