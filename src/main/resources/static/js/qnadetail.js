// 문서가 준비되면 실행
$(document).ready(function () {
    // Summernote 에디터 초기화
    $('#summernote').summernote();

    // 수정 버튼을 선택하고 클릭 이벤트에 modeChange 함수 추가
    const editButton = document.querySelector("#edit-button");
    editButton.addEventListener('click', modeChange);

    // 취소 버튼을 선택하고 클릭 이벤트에 modeChange 함수 추가
    const cancelButton = document.querySelector("#cancel-button");
    cancelButton.addEventListener('click', modeChange);
	
	// 삭제 폼의 제출 이벤트에 삭제 확인 함수 추가
	   const deleteForm = document.querySelector('form[method="post"]');
	   deleteForm.addEventListener('submit', confirmDelete);
});

// 수정 모드 전환 함수
function modeChange() {
    // '#cs>.wrap' 요소에 'edit-mode' 클래스를 토글하여 수정 모드 전환
    document.querySelector('#cs>.wrap').classList.toggle('edit-mode');
}
// 수정 확인 함수
function confirmEdit(event) {
    // 사용자에게 수정 확인 메시지 표시
    const isConfirmed = confirm("수정하시겠습니까?");
    if (isConfirmed) {
        // 확인 버튼 클릭 시 수정 폼 제출
        document.querySelector('#edit-form form').submit();
    }
}

// 삭제 확인 함수
function confirmDelete(event) {
    // 기본 폼 제출 동작을 방지
    event.preventDefault();

    // 사용자에게 삭제 확인 메시지 표시
    const isConfirmed = confirm("정말로 삭제하시겠습니까?");
    if (isConfirmed) {
        // 확인 버튼 클릭 시 폼 제출
        event.target.submit();
    }
}
