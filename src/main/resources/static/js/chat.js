let socket;

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
        if (!socket || socket.readyState !== WebSocket.OPEN) {
            connectWebSocket();
        }
    } else {
        popup.style.display = 'none';
    }
}

function connectWebSocket() {
    socket = new WebSocket("ws://localhost:8080/chatbot");

    socket.onopen = function(e) {
        console.log("[open] 연결이 설정되었습니다.");
    };

    socket.onmessage = function(event) {
        console.log(`[message] 서버로부터 데이터 수신: ${event.data}`);
        displayMessage(event.data, 'bot');
    };

    socket.onclose = function(event) {
        if (event.wasClean) {
            console.log(`[close] 연결이 정상적으로 종료되었습니다. 코드=${event.code} 이유=${event.reason}`);
        } else {
            console.log('[close] 연결이 종료되었습니다.');
        }
    };

    socket.onerror = function(error) {
        console.log(`[error] ${error.message}`);
    };
}

function sendMessage() {
    const inputElement = document.getElementById("chat-input");
    const message = inputElement.value;
    if (message) {
        socket.send(message);
        displayMessage(message, 'user');
        inputElement.value = '';
    }
}

function displayMessage(message, sender) {
    const chatMessages = document.getElementById("chat-messages");
    const messageElement = document.createElement("div");
    messageElement.classList.add("chat-message", sender);
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}