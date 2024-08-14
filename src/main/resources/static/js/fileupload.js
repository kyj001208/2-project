/**
 * 
 */
//이미지 업로드 처리하는 함수
function uploadImage(url, formData){
	// CSRF 토큰과 헤더 이름을 메타 태그에서 가져옵니다
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

	// POST 요청으로 파일 업로드
	return fetch(url,{
		method: "POST",
		body: formData, //업로드할 파일이 담긴 FormData
		headers: {
            [csrfHeader]: csrfToken  // CSRF 토큰을 동적으로 헤더에 추가
        }
	})
	//.then(response => response.text())
	.then(response => response.json())//Map<String, String> json형식으로 받아진다
	.catch(error => {
		console.log('Error:', error);
		throw error;//에러를 호출한 곳으로 전달
	})
	//.finally()
	;
}

//이미지 유효성 검사하는 함수
function imageCheckSuccess(files) {
    if (files.length === 0) {
        console.log("파일이 선택되지 않았습니다.");
        return false;
    }
    const file = files[0];
    const MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB

    if (!file.type.startsWith('image/')) {
        alert("이미지 파일만 업로드 가능합니다.");
        return false;
    }
    if (file.size > MAX_FILE_SIZE) {
        alert(`파일 크기는 2MB 이하여야 합니다. (현재 크기: ${(file.size / 1024 / 1024).toFixed(2)}MB)`);
        return false;
    }
    return true; //이미지파일이면 true로 반환
}
// hidden 타입의 input 요소를 동적으로 생성 
//-> tempKey와 orgName은 JavaScript에 의해 동적으로 설정되므로, 필요한 값이 설정되지 않으면 서버에 전달되지 않습니다.
// 업로드된 이미지 정보 보여주고 hidden,input에 값을 저장하는 함수
function setHiddenValueAndShowImg2(result, input) {
    const url = result.url;
    const tempKey = result.tempKey;
    const orgName = result.orgName;

    input.parentElement.style.backgroundImage = `url(${url})`;
    input.parentElement.style.backgroundColor = "transparent";

    // tempKey를 위한 hidden input 처리
    let tempKeyInput = input.nextElementSibling;
    if (!tempKeyInput || tempKeyInput.name !== "tempKey") {
		// tempKey를 위한 hidden input이 없거나, 이름이 다르면 생성
        tempKeyInput = document.createElement("input");
        tempKeyInput.type = "hidden";
        tempKeyInput.name = "tempKey"; 
        input.parentNode.insertBefore(tempKeyInput, input.nextSibling);
    }
    tempKeyInput.value = tempKey;

    // orgName을 위한 hidden input 처리
    let orgNameInput = tempKeyInput.nextElementSibling;
    if (!orgNameInput || orgNameInput.name !== "orgName") {
		// orgName을 위한 hidden input이 없거나, 이름이 다르면 생성
        orgNameInput = document.createElement("input");
        orgNameInput.type = "hidden";
        orgNameInput.name = "orgName";
        tempKeyInput.parentNode.insertBefore(orgNameInput, tempKeyInput.nextSibling);
    }
    orgNameInput.value = orgName;
}
//서브이미지없이 업로드하기
function handleMainImageUploadSuccess(result, input) {
    // 메인 이미지 업로드 성공 시 처리
    setHiddenValueAndShowImg2(result, input);
    
    // 서브 이미지가 없는 경우에도 바로 다음 단계를 진행하도록 추가
    if (!document.querySelector('.sub-images .fileUpload input[type="file"]').files.length) {
        // 서브 이미지가 없으면 다음 단계로 진행
        proceedToNextStep(); // 이 함수는 메인 이미지 업로드 이후에 처리할 다음 단계 로직을 정의해야 합니다.
    }
}

// 다음 단계로 넘어가는 함수 (예시로 작성)
function proceedToNextStep() {
    console.log("메인 이미지 업로드 성공! 서브 이미지 없이도 다음 단계로 진행됩니다.");
    // 여기에 다음에 해야 할 작업을 추가합니다.
}


//파일 업로드와 유효성검사
function fileupload(input){
	const files=input.files;
	
	if(!imageCheckSuccess(files))return; //이미지 유효성 검사 실패시 종료
	
	var formData=new FormData();	 //오른쪽은 대문자로, FormData 객체 생성
	formData.append("itemFile",input.files[0]); // 첫 번째 파일을 FormData에 추가
	// 파일을 서버로 업로드 (임시 업로드)
	uploadImage("/admin/fileupload", formData)
		.then(result=>{
			setHiddenValueAndShowImg2(result,input); 
		})
		.catch(error=>{
			alert("파일업로드 실패! : "+ error.response.status);
		});
}

//추가이미지에서 새 lable 추가하는 함수
function updateOrAddFileUploadLabel(currentInput) {
    const subImagesContainer = currentInput.closest('.sub-images');
    const labels = subImagesContainer.querySelectorAll('.fileUpload');
    
    // 비어있는 라벨 찾기
    const emptyLabel = Array.from(labels).find(label => 
        !label.querySelector('img') && label.querySelector('input[type="file"]').files.length === 0
    );
//빈 lable이 있는데 lable 생성되는걸 방지하는 전략
    if (emptyLabel) {
        // 비어있는 라벨이 있다면 새 라벨을 추가하지 않음
        return;
    }

    // 최대 4개의 이미지만 허용
    if (labels.length >= 4) return;

    // 추가이미지를 위한 새로운 label 요소를 동적으로 생성
    const newLabel = document.createElement('label');
    newLabel.className = 'fileUpload';
    newLabel.innerHTML = `
        <input type="file" accept="image/*" onchange="subFileupload(this)" style="display: none;">
        <span>+</span>
    `;

    subImagesContainer.appendChild(newLabel);
}

//추가이미지 업로드 처리하는 함수
function subFileupload(input){
	const files=input.files;
	
	if(!imageCheckSuccess(files))return;
	
	var formData=new FormData();	 // FormData 객체 생성
	formData.append("itemFile",input.files[0]); // 첫 번째 파일을 FormData에 추가
	// 파일을 서버로 업로드 (임시 업로드)
	uploadImage("/admin/fileupload", formData)
		.then(result=>{
			setHiddenValueAndShowImg2(result,input);
			
			updateOrAddFileUploadLabel(input);
		})
		.catch(error=>{
			alert("파일업로드 실패! : "+ error.response.status);
		});
}

