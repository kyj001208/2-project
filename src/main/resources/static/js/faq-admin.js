
document.addEventListener("DOMContentLoaded", function() {
    // Summernote 에디터 초기화
    var summernote = document.getElementById('summernote');
    // Summernote 초기화하는 부분은 여전히 jQuery 플러그인에 의존해야 하므로 그대로 둘 수 있습니다.
    $(summernote).summernote();
	
	faqFetchApi(`/admin/customer/faq/1`);
	

    var csButtons = document.querySelectorAll('.button-container > button');
    csButtons.forEach(function(button, idx) {
        button.addEventListener('click', function() {
            // 모든 버튼에서 active 클래스 제거
            csButtons.forEach(function(btn) {
                btn.classList.remove('active');
            });

            // 클릭된 버튼에 active 클래스 추가
            button.classList.add('active');

			//content 갖고오기
			//console.log("idx:",idx);
			let division=idx+1;
			faqFetchApi(`/admin/customer/faq/${division}`);
			
           
        });
    });
	
});





