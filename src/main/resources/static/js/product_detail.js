/**
 *
 * @param element
 */
function quantityChange(element) {
    // 입력 필드에서 수량 값을 가져옵니다.
    let quantity = parseInt(element.value);
    console.log('quantity:',quantity);
    // 수량이 양의 정수인지 확인하고, 그렇지 않다면 수량을 1로 설정합니다.
    if (isNaN(quantity) || quantity < 1) {
        quantity = 1;
        element.value = quantity;
    }

    // data-discount_price 속성에서 할인 가격을 가져옵니다.
    const discountPrice = parseInt(document.querySelector('#numberBox').getAttribute('data-discount_price'));
    //console.log('discountPrice:',discountPrice);
    // 총 가격을 계산합니다.
    const totalPrice = discountPrice * quantity;
    // toLocaleString('ko-KR'); JavaScript에서 숫자를 특정 지역의 언어와 문화에 맞는 형식으로 변환하여 문자열로 반환하는 메서드
    const strPrice=totalPrice.toLocaleString('ko-KR');
    //console.log('strPrice:',strPrice);
    // 계산된 총 가격을 DOM에 업데이트합니다.
    document.querySelector('#count_tit .num').innerText = quantity;
    document.querySelector('#totalPrice strong').textContent = strPrice;
}

/**
 *
 * @param button
 */
function btnPlusClick(event, button) {
    event.preventDefault();
    // 수량 입력 필드를 가져옵니다.
    var quantityInput = button.parentElement.querySelector('#quantity');
    var currentQuantity = parseInt(quantityInput.value);

    // 수량을 1 증가시킵니다.
    if (currentQuantity < 999) { // 최대 수량 999로 제한
        quantityInput.value = currentQuantity + 1;
    }

    // 가격 업데이트
    //1. 함수를 호출하는 방법 가능
    //quantityChange(quantityInput);
    //2. onchange 이벤트 강제 발생
    event = new Event('change', { bubbles: true });
    quantityInput.dispatchEvent(event);
}

/**
 *
 * @param button
 */
function btnMinusClick(event, button) {
    event.preventDefault();
    // 수량 입력 필드를 가져옵니다.
    var quantityInput = button.parentElement.querySelector('#quantity');
    var currentQuantity = parseInt(quantityInput.value);

    // 수량을 1 감소시킵니다. 최소 수량은 1입니다.
    if (currentQuantity > 1) {
        quantityInput.value = currentQuantity - 1;
    }

    // 가격 업데이트
    //quantityChange(quantityInput);
    // onchange 이벤트 강제 발생
    event = new Event('change', { bubbles: true });
    quantityInput.dispatchEvent(event);
}

