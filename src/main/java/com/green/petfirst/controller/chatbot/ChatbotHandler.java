package com.green.petfirst.controller.chatbot;

import com.green.petfirst.service.chatbot.ChatbotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import kr.co.shineware.nlp.komoran.core.Komoran;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatbotHandler는 WebSocket 연결을 통한 실시간 채팅을 처리하는 핸들러 클래스입니다.
 * 이 클래스는 WebSocket 연결의 수립, 메시지 처리, 오류 처리, 그리고 연결 종료를 관리합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatbotHandler extends TextWebSocketHandler {

    // ChatbotService 주입
    private final ChatbotService chatbotService;

    // WebSocket 세션과 세션 ID를 매핑하는 맵
    private final Map<WebSocketSession, String> sessionIds = new ConcurrentHashMap<>();

    /**
     * WebSocket 연결이 수립되었을 때 호출되는 메서드입니다.
     * 새로운 세션 ID를 생성하고 로깅합니다.
     *
     * @param session 새로 수립된 WebSocket 세션
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String sessionId = session.getId();
        sessionIds.put(session, sessionId);
        log.info("New WebSocket connection established. Session ID: {}", sessionId);
    }

    /**
     * 클라이언트로부터 텍스트 메시지를 받았을 때 호출되는 메서드입니다.
     * 받은 메시지를 처리하고 챗봇의 응답을 클라이언트에게 전송합니다.
     *
     * @param session 메시지를 보낸 WebSocket 세션
     * @param message 받은 텍스트 메시지
     * @throws Exception 메시지 처리 중 발생할 수 있는 예외
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionId = sessionIds.get(session);
        String userInput = message.getPayload();
        log.info("Received message from session {}: {}", sessionId, userInput);

        try {
            String response = chatbotService.getResponse(userInput, sessionId);
            session.sendMessage(new TextMessage(response));
        } catch (Exception e) {
            log.error("Error processing message for session {}: {}", sessionId, e.getMessage(), e);
            session.sendMessage(new TextMessage("죄송합니다. 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요."));
        }
    }

    /**
     * WebSocket 연결에서 전송 오류가 발생했을 때 호출되는 메서드입니다.
     *
     * @param session 오류가 발생한 WebSocket 세션
     * @param exception 발생한 예외
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("Transport error in session {}: {}", session.getId(), exception.getMessage(), exception);
    }

    /**
     * WebSocket 연결이 종료되었을 때 호출되는 메서드입니다.
     * 세션 ID를 맵에서 제거하고 로깅합니다.
     *
     * @param session 종료된 WebSocket 세션
     * @param status 연결 종료 상태
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String sessionId = sessionIds.remove(session);
        log.info("WebSocket connection closed for session {}. Status: {}", sessionId, status);
    }
}