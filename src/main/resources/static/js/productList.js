function btnDeleteClicked() {
    if (!confirm('정말로 삭제하시겠습니까?')) {
        return false; // 삭제 취소 시, 아무런 작업도 하지 않음
    }

    // 폼을 직접 제출하여 서버의 리다이렉트 처리를 따름
    var form = document.getElementById('deleteForm');
    form.submit();

    // 기본 폼 제출 동작을 막지 않음으로써 서버의 리다이렉트 응답을 그대로 처리
    return true;
}
