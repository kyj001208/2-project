$(document).ready(function() {
    // CSRF 토큰 및 헤더 설정
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // 버튼 클릭 이벤트 핸들러
    $("#edit-btn").click(function() {
        $(".display").hide();
        $(".edit").show();
        $("#edit-btn").hide();
        $("#update-btn").show();
        $("#cancel-btn").show();
    });

    $("#cancel-btn").click(function() {
        $(".display").show();
        $(".edit").hide();
        $("#edit-btn").show();
        $("#update-btn").hide();
        $("#cancel-btn").hide();
    });

    // 폼 제출 이벤트 핸들러
    $("#member-form").submit(function(event) {
        event.preventDefault(); // 폼 제출 기본 동작 방지

        // 폼 데이터를 JSON 형식으로 변환
        const formData = {
			id: $("#id").val(),
            email: $("#email").val(),
            userId: $("#userId").val(),
            phone: $("#phone").val(),
            address: $("#address").val(),
            petName: $("#petName").val(),
            petBreed: $("#petBreed").val()
        };

        $.ajax({
            url: "/petfir/mypage/myinfo/update",
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData), // 올바른 JSON 데이터 전송
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken); // CSRF 토큰 헤더 설정
            },
            success: function(response) {
                location.reload();  // 페이지 새로 고침
            },
            error: function(xhr, status, error) {
                const errorMessage = xhr.responseJSON && xhr.responseJSON.message ? xhr.responseJSON.message : '저장 중 오류가 발생했습니다.';
                alert(errorMessage);
            }
        });
    });
});
