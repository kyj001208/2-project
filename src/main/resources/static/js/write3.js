$(document).ready(function () {
    // Summernote 에디터 초기화
    $('#summernote').summernote();
});

function redirectToList() {
    // 브라우저를 Q&A 리스트 페이지로 이동
    window.location.href = "http://localhost:8080/common/customer/qna";
}
