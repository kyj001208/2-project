//DOMContentLoaded:HTML 문서의 모든 요소가 로드된 후에 자바스크립트가 실행

document.addEventListener('DOMContentLoaded', function() {

	const passwordInput = document.getElementById('password');

    const confirmPasswordInput = document.getElementById('password_1');

    const signUpButton = document.querySelector('.sign_but');


    function validatePassword() {

        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        //(영문 대소문자/숫자/특수문자 1가지 이상 조합,8자~15자)
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{};':"\\|,.<>/?])[A-Za-z\d!@#$%^&*()_+[\]{};':"\\|,.<>/?]{8,15}$/;

        if (!passwordRegex.test(password)) {
          	 	Swal.fire({
  				icon: "error",	
  				title: "비밀번호 오류",
  				text: "영문 대소문자/숫자/특수문자 1가지씩 포함하여 조합,8자~15자",
  				footer: '<a href="#">왜 이문제가 발생하나요?</a>'
});
            return false;

        }
        if (password !== confirmPassword) {
          		 Swal.fire({
  				icon: "error",
  				title: "비밀번호 일치 오류",
  				text: "비밀번호가 일치하지 않습니다. 다시 입력해주세요",
  				footer: '<a href="#">왜 이문제가 발생하나요?</a>'
});
            return false;
        }
        return true;
    }

    signUpButton.addEventListener('click', function(event) {
        if (!validatePassword()) {
            event.preventDefault(); // 유효성 검사 실패 시 폼 제출 막기
        }

    });

});



function sample6_execDaumPostcode() {

        new daum.Postcode({
            oncomplete: function(data) {

                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.

                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }



                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.

                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.

                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;

                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.

                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);

                    }

                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }

                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }



                // 우편번호와 주소 정보를 해당 필드에 넣는다.

                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();

            }
        }).open();

    }