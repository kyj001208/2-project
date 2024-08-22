// 문서가 준비되면 실행
$(document).ready(function () {
    // Summernote 에디터 초기화
    $('#summernote').summernote();
});

// 공지사항 리스트 페이지로 리디렉션하는 함수
function redirectToList() {
    // 브라우저를 공지사항 리스트 페이지로 이동
    window.location.href = "http://localhost:8080/admin/customer/notices";
}
