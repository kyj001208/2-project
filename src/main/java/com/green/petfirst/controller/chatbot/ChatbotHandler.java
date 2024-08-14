package com.green.petfirst.controller.chatbot;

import com.green.petfirst.service.chatbot.ChatbotService;
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

    private final ChatbotService chatbotService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userInput = message.getPayload();
        log.info("Received message: {}", userInput);

        String response = chatbotService.getResponse(userInput);

        session.sendMessage(new TextMessage(response));
    }
}