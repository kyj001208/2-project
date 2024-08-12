package com.green.petfirst.controller.chatbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import kr.co.shineware.nlp.komoran.core.Komoran;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatbotHandler extends TextWebSocketHandler {

    private final Komoran komoran;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userInput = message.getPayload();
        log.info("Received message: {}", userInput);

        List<String> keywords = extractKeywords(userInput);
        String response = generateResponse(keywords);

        session.sendMessage(new TextMessage(response));
    }

    private List<String> extractKeywords(String text) {
        return komoran.analyze(text).getNouns();
    }

    private String generateResponse(List<String> keywords) {
        // 여기에 키워드를 기반으로 응답을 생성하는 로직을 구현하세요
        return "키워드를 받았습니다: " + String.join(", ", keywords);
    }
}
