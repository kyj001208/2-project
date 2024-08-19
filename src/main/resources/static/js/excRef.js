const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

// 모달 열기
function openModal(modalId) {
	document.getElementById(modalId).style.display = "block";
}

// 모달 닫기
function closeModal(modalId) {
	document.getElementById(modalId).style.display = "none";
}

function openTab(tabName) {
	var i, tabcontent, tablinks;

	// Hide all tab contents
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Remove the 'active' class from all tabs
	tablinks = document.querySelectorAll(".tab div");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].classList.remove("active");
	}

	// Show the current tab and add 'active' class
	document.getElementById(tabName).style.display = "block";
	event.currentTarget.classList.add("active");
}

// 모달 열기
function openModal(modalId, requestNo) {
	const modal = document.getElementById(modalId);
	const button = document.querySelector(`button[data-request-no='${requestNo}']`);

	if (button) {
		const requestStatus = button.getAttribute('data-request-status');
		const requestDate = button.getAttribute('data-request-date');
		const orderNo = button.getAttribute('data-order-no');
		const memberName = button.getAttribute('data-member-name');
		const reason = button.getAttribute('data-reason');

		// 교환 모달
		if (modalId === 'exchangeModal') {
			modal.querySelector('.detail.requestStatus').textContent = requestStatus;
			modal.querySelector('.detail.date').textContent = requestDate;
			modal.querySelector('.detail.requestNo').textContent = requestNo;
			modal.querySelector('.detail.orderNo').textContent = orderNo;
			modal.querySelector('.detail.memberName').textContent = memberName;
			modal.querySelector('.detail.reason').textContent = reason;
		}

		// 환불 모달
		if (modalId === 'refundModal') {
			modal.querySelector('.detail.requestStatus').textContent = requestStatus;
			modal.querySelector('.detail.date').textContent = requestDate;
			modal.querySelector('.detail.requestNo').textContent = requestNo;
			modal.querySelector('.detail.orderNo').textContent = orderNo;
			modal.querySelector('.detail.memberName').textContent = memberName;
			modal.querySelector('.detail.reason').textContent = reason;
		}
	}

	modal.style.display = "block";
}

// 모달 닫기
function closeModal(modalId) {
	document.getElementById(modalId).style.display = "none";
}

function openOptionsModal(requestType, buttonElement) {
	const requestNo = buttonElement.getAttribute('data-request-no');  // data-request-no 속성 값 읽기

	if (requestType === 'EXCHANGE') {
		$('#exchangeOptionsModal').data('requestNo', requestNo);  // 요청 번호 저장
		$('#exchangeOptionsModal').show();
	} else if (requestType === 'REFUND') {
		$('#refundOptionsModal').data('requestNo', requestNo);  // 요청 번호 저장
		$('#refundOptionsModal').show();
	}
}


function closeModal(modalId) {
	$(`#${modalId}`).hide();
}

function updateStatus(requestType, action) {
    const modalId = requestType === 'EXCHANGE' ? 'exchangeOptionsModal' : 'refundOptionsModal';
    var requestNo = $(`#${modalId}`).data('requestNo');

    var requestStatus = action === 'APPROVE' ? 
        (requestType === 'EXCHANGE' ? 'EXCHANGE_DONE' : 'REFUND_DONE') : 
        (requestType === 'EXCHANGE' ? 'EXCHANGE_CANCELLED' : 'REFUND_CANCELLED');

    // AJAX 요청
    $.ajax({
        headers: {
            [header]: token
        },
        url: `/admin/exchangeRefund/${requestNo}`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            requestNo: requestNo,
            requestStatus: requestStatus,
            requestType: requestType
        }),
        success: function(response) {
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error('상태 업데이트 실패:', error);
            alert('상태 업데이트 실패: ' + xhr.responseText);
        }
    });

    closeModal(modalId);
}
