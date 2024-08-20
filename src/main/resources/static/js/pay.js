function calculateTotalAmount() {
    console.log('calculateTotalAmount function called');
    let totalAmount = 0;
    const prices = document.querySelectorAll('.cart-price');

    prices.forEach(function(priceElement) {
        const textPrice = priceElement.textContent.trim();
        console.log('Price text:', textPrice);
        const price = parseFloat(textPrice.replace(/,/g, '').replace('원', ''));
        if (!isNaN(price)) {
            totalAmount += price;
        }
    });

    console.log('Total amount calculated:', totalAmount.toLocaleString() + '원');
    document.getElementById('totalAmount').innerText = totalAmount.toLocaleString() + '원';
}
