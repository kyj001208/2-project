package com.green.petfirst.controller.chatbot;

import com.green.petfirst.service.chatbot.ChatbotService;
import kr.co.shineware.nlp.komoran.model.Token;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ChatController는 챗봇 관련 HTTP 요청을 처리하는 REST 컨트롤러입니다.
 * 초기 메시지 제공, 채팅 응답 생성, 그리고 텍스트 분석 기능을 제공합니다.
 */
@RestController
@RequestMapping("/api")
public class ChatController {

    // Komoran 형태소 분석기 인스턴스 생성
    private final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

    // ChatbotService 주입
    @Autowired
    private ChatbotService chatbotService;

    /**
     * 챗봇의 초기 메시지를 반환합니다.
     *
     * @return 초기 인사 메시지
     */
    @GetMapping("/chatbot/initial-message")
    public String getInitialMessage() {
        return chatbotService.getInitialMessage();
    }

    /**
     * 사용자 메시지에 대한 챗봇의 응답을 생성합니다.
     *
     * @param request 사용자 메시지와 세션 ID를 포함한 요청 맵
     * @return 챗봇의 응답 메시지
     */
    @PostMapping("/chatbot/message")
    public String getChatbotResponse(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String sessionId = request.get("sessionId");
        return chatbotService.getResponse(message, sessionId);
    }

    /**
     * 주어진 텍스트를 형태소 분석하고 키워드를 추출합니다.
     *
     * @param request 분석할 텍스트를 포함한 요청 맵
     * @return 분석 결과와 추출된 키워드를 포함한 응답 맵
     */
    @PostMapping("/analyze")
    public @ResponseBody Map<String, Object> analyze(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        KomoranResult result = komoran.analyze(message);

        List<String> keywords = extractKeywords(result);

        Map<String, Object> response = new HashMap<>();
        response.put("analysis", result.getPlainText());
        response.put("keywords", keywords);

        return response;
    }

    /**
     * Komoran 분석 결과에서 명사(NNG, NNP)만을 추출합니다.
     *
     * @param result Komoran 분석 결과
     * @return 추출된 키워드 리스트
     */
    private List<String> extractKeywords(KomoranResult result) {
        return result.getTokenList().stream()
                .filter(token -> "NNG".equals(token.getPos()) || "NNP".equals(token.getPos()))
                .map(Token::getMorph)
                .collect(Collectors.toList());
    }
}