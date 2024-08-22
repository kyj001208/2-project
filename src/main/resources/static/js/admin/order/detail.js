// 주문 상세 정보를 로드하는 함수
function loadOrderDetail(orderNo, event) {
	fetch(`/admin/order/${encodeURIComponent(orderNo)}`, {
		method: 'GET',
		headers: {
			'Accept': 'text/html',  // 서버가 HTML을 반환하도록 요청
		}
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();  // HTML 응답을 텍스트로 처리
		})
		.then(data => {
			const detailsCard = document.getElementById('tooltip');
			detailsCard.innerHTML = data;
			showTooltip(event);  // 이벤트를 넘겨서 클릭 위치를 사용
		})
		.catch(error => console.error('Error loading order details:', error));
}

// 회원의 주문 목록을 로드하는 함수
function loadMemberOrder(memNo, event) {
    fetch(`/admin/member/${encodeURIComponent(memNo)}`, {
        method: 'GET',
        headers: {
            'Accept': 'text/html',  // 서버가 HTML을 반환하도록 요청
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();  // HTML 응답을 텍스트로 처리
    })
    .then(data => {
        const detailsCard = document.getElementById('tooltip');
        detailsCard.innerHTML = data;
        showTooltip(event);  // 이벤트를 넘겨서 클릭 위치를 사용
    })
    .catch(error => console.error('Error loading member order details:', error));
}


// 툴팁을 보여주는 함수
function showTooltip(event) {
	const tooltip = document.getElementById('tooltip');
	tooltip.classList.add('show-tooltip');

	// 클릭한 위치에 툴팁을 표시
	tooltip.style.top = `${event.clientY + 10}px`; // 마우스 클릭 위치 아래에 표시
	tooltip.style.left = `${event.clientX + 10}px`;
}

// 툴팁을 닫는 함수
function closeTooltip() {
	const tooltip = document.getElementById('tooltip');
	tooltip.classList.remove('show-tooltip');
}



