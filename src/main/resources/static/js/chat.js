// WebSocket 연결을 위한 전역 변수
let socket;
let isConnecting = false;
let reconnectAttempts = 0;
const maxReconnectAttempts = 5;

// 페이지 로드 시 팝업을 숨김 상태로 설정하고 엔터 키 이벤트 리스너 추가
window.addEventListener('load', function() {
    const popup = document.getElementById('chat-popup');
    popup.style.display = 'none';

    // 입력 필드에 엔터 키 이벤트 리스너 추가
    const inputElement = document.getElementById("chat-input");
    inputElement.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault(); // 폼 제출 방지
            sendMessage();
        }
    });
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
/**
 * 초기 메시지를 서버로부터 가져와 표시하는 함수
 */
function getInitialMessage() {
    fetch('/api/chatbot/initial-message')
        .then(response => response.text())
        .then(message => {
            displayMessage(message, 'bot');
        })
        .catch(error => {
            console.error('Error fetching initial message:', error);
            displayMessage("죄송합니다. 초기 메시지를 불러오는 데 문제가 발생했습니다.", 'bot');
        });
}
/**
 * WebSocket 연결을 설정하는 함수
 */
function connectWebSocket() {
    if (isConnecting) {
        console.log('WebSocket connection attempt is already in progress.');
        return;
    }

    isConnecting = true;

    socket = new WebSocket("ws://localhost:8080/chatbot");

    socket.onopen = function(e) {
        console.log("[open] 연결이 설정되었습니다.");
        isConnecting = false;
        reconnectAttempts = 0;
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
        isConnecting = false;

        if (reconnectAttempts < maxReconnectAttempts) {
            reconnectAttempts++;
            console.log(`Attempting to reconnect (${reconnectAttempts}/${maxReconnectAttempts})...`);
            setTimeout(connectWebSocket, 3000);
        } else {
            console.log('Max reconnect attempts reached. Please refresh the page.');
            displayMessage('서버와의 연결이 끊어졌습니다. 페이지를 새로고침해 주세요.', 'error');
        }
    };

    socket.onerror = function(error) {
        console.log(`[error] ${error.message}`);
        isConnecting = false;
    };
}

/**
 * 메시지를 서버로 전송하는 함수
 */
function sendMessage() {
    const inputElement = document.getElementById("chat-input");
    const message = inputElement.value.trim();
    if (message) {
        if (socket && socket.readyState === WebSocket.OPEN) {
            try {
                socket.send(message);
                displayMessage(message, 'user');
                inputElement.value = '';
            } catch (error) {
                console.error('Error sending message:', error);
                displayMessage('메시지 전송 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.', 'error');
            }
        } else {
            console.log('WebSocket is not open. Attempting to reconnect...');
            connectWebSocket();
            setTimeout(() => sendMessage(), 1000);
        }
    }
}
/**
 * 메시지를 화면에 표시하는 함수
 */
function displayMessage(message, sender) {
    const chatMessages = document.getElementById("chat-messages");
    const messageElement = document.createElement("div");
    messageElement.classList.add("chat-message", sender);
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// 주기적으로 연결 상태 확인 및 필요 시 재연결
setInterval(() => {
    if ((!socket || socket.readyState === WebSocket.CLOSED) && document.getElementById('chat-popup').style.display !== 'none') {
        console.log('WebSocket is closed. Attempting to reconnect...');
        connectWebSocket();
    }
}, 5000);