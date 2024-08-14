package com.green.petfirst.service.chatbot;


import com.green.petfirst.domain.entity.ChatbotResponse;
import com.green.petfirst.domain.repository.ChatbotResponseRepository;
import kr.co.shineware.nlp.komoran.core.Komoran;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final Komoran komoran;
    private final ChatbotResponseRepository chatbotResponseRepository;

    public String getResponse(String input) {
        List<String> keywords = extractKeywords(input);
        return generateResponse(keywords);
    }

    private List<String> extractKeywords(String text) {
        return komoran.analyze(text).getNouns();
    }

    private String generateResponse(List<String> keywords) {
        Optional<ChatbotResponse> bestMatch = findBestMatch(keywords);

        if (bestMatch.isPresent()) {
            return bestMatch.get().getResponseText();
        } else {
            return "죄송합니다. 해당 질문에 대한 답변을 찾을 수 없습니다.";
        }
    }

    private Optional<ChatbotResponse> findBestMatch(List<String> keywords) {
        List<ChatbotResponse> allResponses = chatbotResponseRepository.findAll();

        return allResponses.stream()
                .filter(response -> keywordsMatch(response.getKeywords(), keywords))
                .findFirst();
    }

    private boolean keywordsMatch(String responseKeywords, List<String> inputKeywords) {
        List<String> responseKeywordList = List.of(responseKeywords.split(","));
        return inputKeywords.stream().anyMatch(responseKeywordList::contains);
    }
}