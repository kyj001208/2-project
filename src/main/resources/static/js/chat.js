// chat.js

// 페이지 로드 시 팝업을 숨김 상태로 설정
window.addEventListener('load', function() {
    const popup = document.getElementById('chat-popup');
    popup.style.display = 'none';
});

// 채팅 팝업 토글 함수
function toggleChatPopup() {
    const popup = document.getElementById('chat-popup');
    if (popup.style.display === 'none' || popup.style.display === '') {
        popup.style.display = 'flex';
    } else {
        popup.style.display = 'none';
    }
}

// 메시지 전송 함수
function sendMessage() {
    const input = document.getElementById('chat-input');
    const messages = document.getElementById('chat-messages');

    if (input.value.trim()) {
        // 사용자 메시지 추가
        addMessage(input.value, 'user');

        // 챗봇 응답 생성 (여기서는 간단한 예시를 사용)
        setTimeout(() => {
            const botResponse = generateBotResponse(input.value);
            addMessage(botResponse, 'bot');
        }, 500); // 500ms 지연 후 응답
        input.value = ''; // 입력 필드 비우기
        messages.scrollTop = messages.scrollHeight; // 스크롤을 최신 메시지로 이동
    }
}

// 메시지 추가 함수
function addMessage(text, sender) {
    const messages = document.getElementById('chat-messages');
    const message = document.createElement('div');
    message.className = 'chat-message ' + sender;
    message.textContent = text;
    messages.appendChild(message);
}

// 챗봇 응답 생성 함수
function generateBotResponse(userMessage) {
    if (userMessage.includes('안녕하세요')) {
        return '안녕하세요! 어떻게 도와드릴까요?';
    } else if (userMessage.includes('가격')) {
        return '제품 가격에 대해 문의주셨군요. 자세한 사항은 웹사이트에서 확인해주세요.';
    } else {
        return '죄송하지만, 이해하지 못했습니다. 다른 질문이 있으신가요?';
    }
}
