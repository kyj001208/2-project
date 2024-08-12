package com.green.petfirst.controller.chatbot;

import kr.co.shineware.nlp.komoran.model.Token;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL); // FORMAL 모델 사용

    @PostMapping("/analyze")
    public @ResponseBody Map<String, Object> analyze(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        KomoranResult result = komoran.analyze(message);

        // 형태소 분석 결과에서 키워드 추출
        List<String> keywords = extractKeywords(result);

        Map<String, Object> response = new HashMap<>();
        response.put("analysis", result.getPlainText()); // 전체 분석 결과
        response.put("keywords", keywords); // 추출된 키워드 목록

        return response;
    }

    private List<String> extractKeywords(KomoranResult result) {
        return result.getTokenList().stream()
                .filter(token -> "NNG".equals(token.getPos()) || "NNP".equals(token.getPos())) // 명사(Noun) 필터
                .map(Token::getMorph)
                .collect(Collectors.toList());
    }
}
