package com.green.petfirst.service.chatbot;

import com.green.petfirst.domain.entity.ChatbotResponse;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.ChatbotResponseRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * ChatbotService 클래스는 챗봇의 핵심 로직을 처리합니다.
 * 사용자 입력을 처리하고, 적절한 응답을 생성하며, 대화 컨텍스트를 관리합니다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChatbotService {

    private final ChatbotResponseRepository chatbotResponseRepository;
    private final ProductRepository productRepository;
    // 세션별 대화 컨텍스트를 관리하는 맵
    private final Map<String, String> sessionContexts = new ConcurrentHashMap<>();
    // 유효한 키워드 목록
    private final List<String> validKeywords = Arrays.asList("강아지", "고양이", "사료", "간식", "장난감", "용품");
    /**
     * 챗봇의 초기 인사 메시지를 반환합니다.
     */
    public String getInitialMessage() {
        return "안녕하세요! 반려동물 용품 전문점 PetFirst입니다. 어떤 도움이 필요하신가요?";
    }
    /**
     * 사용자 입력에 대한 응답을 생성합니다.
     * @param input 사용자 입력
     * @param sessionId 세션 ID
     * @return 챗봇의 응답
     */
    public String getResponse(String input, String sessionId) {
        try {
            log.info("Processing input for session {}: {}", sessionId, input);
            // 입력이 유효하지 않고 데이터베이스 키워드도 포함하지 않으면 재질문
            if (!isValidInput(input) && !containsDatabaseKeyword(input)) {
                return generateReQuestion(sessionId);
            }

            String currentContext = sessionContexts.getOrDefault(sessionId, "greeting");
            List<ChatbotResponse> matches = chatbotResponseRepository.findByKeywordsContainingOrContextIdOrderByRelevance(input, currentContext);

            if (matches.isEmpty()) {
                return handleGeneralProductQuery(input);
            }

            ChatbotResponse bestMatch = matches.get(0);
            updateSessionContext(sessionId, bestMatch.getNextContextId());

            String response = bestMatch.getResponseText();
            // 제품 관련 문의인 경우 추가 정보 제공
            if (bestMatch.getContextId().equals("product_inquiry") ||
                    bestMatch.getContextId().equals("specific_product_info")) {
                String productInfo = handleGeneralProductQuery(input);
                if (!productInfo.startsWith("죄송합니다")) {
                    response += "\n\n" + productInfo;
                }
            }

            log.info("Response found for session {}: {}", sessionId, response);
            return response;
        } catch (Exception e) {
            log.error("Error processing input for session {}: {}", sessionId, e.getMessage(), e);
            return "죄송합니다. 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.";
        }
    }
    /**
     * 입력이 유효한 키워드를 포함하는지 확인합니다.
     */
    private boolean isValidInput(String input) {
        return validKeywords.stream().anyMatch(keyword -> input.toLowerCase().contains(keyword.toLowerCase()));
    }
    /**
     * 입력이 데이터베이스에 저장된 키워드를 포함하는지 확인합니다.
     */
    private boolean containsDatabaseKeyword(String input) {
        List<ChatbotResponse> matches = chatbotResponseRepository.findByKeywordsContaining(input);
        return !matches.isEmpty();
    }
    /**
     * 적절한 재질문 메시지를 생성합니다.
     */
    private String generateReQuestion(String sessionId) {
        String currentContext = sessionContexts.get(sessionId);
        if (currentContext == null || currentContext.equals("greeting")) {
            return "죄송합니다. 적절한 응답을 찾기 어렵습니다. 강아지나 고양이 용품 중 어떤 것을 찾으시나요?";
        } else if (currentContext.contains("product_inquiry")) {
            return "어떤 종류의 용품을 찾으시나요? 사료, 간식, 장난감 중에서 말씀해 주세요.";
        } else {
            return "죄송합니다. 적절한 응답을 찾기 어렵습니다. 다시 한 번 말씀해 주시겠어요?";
        }
    }
    /**
     * 특정 제품에 대한 상세 정보를 제공합니다.
     */
    private String handleSpecificProductQuery(List<ProductEntity> products) {
        ProductEntity product = products.get(0);
        StringBuilder response = new StringBuilder();
        response.append("'").append(product.getProductName()).append("' 제품에 대한 정보입니다:\n\n");
        response.append("설명: ").append(product.getProductDetail()).append("\n");
        response.append("가격: ").append(product.getPrice()).append("원");
        if (product.getDiscount() > 0) {
            response.append(", 할인가: ").append(product.getDiscountPrice()).append("원");
        }
        response.append("\n\n이 제품을 구매하시겠습니까? 아니면 다른 제품에 대해 알고 싶으신 점이 있나요?");
        return response.toString();
    }
    /**
     * 일반적인 제품 검색 쿼리를 처리합니다.
     */
    private String handleGeneralProductQuery(String input) {
        List<ProductEntity> products = productRepository.findByProductNameContaining(input);
        if (products.isEmpty()) {
            return "죄송합니다. 해당 제품을 찾을 수 없습니다. 다른 제품명이나 카테고리를 입력해 주시겠어요?";
        }
        StringBuilder response = new StringBuilder("관련 제품들을 찾았습니다:\n");
        for (ProductEntity product : products.subList(0, Math.min(products.size(), 3))) {
            response.append("- ").append(product.getProductName()).append(": ")
                    .append(product.getProductDetail()).append("\n")
                    .append("  가격: ").append(product.getPrice()).append("원");
            if (product.getDiscount() > 0) {
                response.append(", 할인가: ").append(product.getDiscountPrice()).append("원");
            }
            response.append("\n");
        }
        if (products.size() > 3) {
            response.append("더 많은 제품이 있습니다. 특정 제품에 대해 더 자세히 알고 싶으시면 제품명을 말씀해 주세요.");
        }
        return response.toString();
    }
    /**
     * 세션의 대화 컨텍스트를 업데이트합니다.
     */
    private void updateSessionContext(String sessionId, String nextContextId) {
        if (nextContextId != null && !nextContextId.isEmpty()) {
            sessionContexts.put(sessionId, nextContextId);
        }
    }
}