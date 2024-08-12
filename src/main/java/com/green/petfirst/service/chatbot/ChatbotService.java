package com.green.petfirst.service.chatbot;

import kr.co.shineware.nlp.komoran.core.Komoran;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final Komoran komoran;

    public String processMessage(String message) {
        List<String> keywords = extractKeywords(message);
        return generateResponse(keywords);
    }

    private List<String> extractKeywords(String text) {
        return komoran.analyze(text).getNouns();
    }

    private String generateResponse(List<String> keywords) {
        // 키워드를 기반으로 응답을 생성하는 로직을 구현하세요
        return "키워드를 받았습니다: " + String.join(", ", keywords);
    }
}