package com.green.petfirst.service.chatbot;

import com.green.petfirst.domain.entity.ChatbotResponse;
import com.green.petfirst.domain.entity.ProductEntity;
import com.green.petfirst.domain.repository.ChatbotResponseRepository;
import com.green.petfirst.domain.repository.ProductRepository;
import com.green.petfirst.controller.chatbot.ConversationContext; // ConversationContext 클래스 import
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatbotService {

    private final ChatbotResponseRepository chatbotResponseRepository;
    private final ProductRepository productRepository;
    private final Map<String, ConversationContext> sessionContexts = new ConcurrentHashMap<>();
    private final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

    // 사용자의 입력에 따라 적절한 응답을 반환
    public String getResponse(String input, String sessionId) {
        try {
            log.info("Processing input for session {}: {}", sessionId, input);

            // 대화 맥락 초기화
            ConversationContext context = sessionContexts.computeIfAbsent(sessionId, k -> new ConversationContext());
            context.setLastContextId(context.getLastContextId());
            context.setConversationDepth(context.getConversationDepth() + 1); // 대화 깊이 증가

            List<String> extractedKeywords = extractKeywords(input);
            ChatbotResponse bestMatch = findBestMatch(extractedKeywords, context.getLastContextId());

            if (bestMatch == null) {
                // 유사한 응답 찾기
                ChatbotResponse similarResponse = findSimilarResponses(extractedKeywords);
                if (similarResponse != null) {
                    return similarResponse.getResponseText();
                }
                return "죄송합니다. 질문을 이해하지 못했습니다. 더 구체적으로 말씀해 주시겠어요?";
            }

            context.setLastContextId(bestMatch.getNextContextId());
            String response = processResponse(bestMatch, input, context);

            log.info("Response for session {}: {}", sessionId, response);
            return response;
        } catch (Exception e) {
            log.error("Error processing input for session {}: {}", sessionId, e.getMessage(), e);
            return "죄송합니다. 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.";
        }
    }

    // 입력된 문장에서 키워드를 추출하는 메서드
    private List<String> extractKeywords(String input) {
        KomoranResult result = komoran.analyze(input);
        List<String> keywords = result.getNouns();  // 명사만 추출
        log.info("Extracted keywords: {}", keywords);
        return keywords;
    }

    // 최적의 응답을 찾는 메서드
    private ChatbotResponse findBestMatch(List<String> extractedKeywords, String currentContextId) {
        List<ChatbotResponse> possibleResponses = chatbotResponseRepository.findByContextId(currentContextId);
        ChatbotResponse bestMatch = possibleResponses.stream()
                .max(Comparator.comparingInt(response -> countMatchingKeywords(response, extractedKeywords)))
                .orElse(null);

        log.info("Best match for keywords {}: {}", extractedKeywords, bestMatch != null ? bestMatch.getResponseText() : "No match found");
        return bestMatch;
    }

    // 비슷한 응답 찾기
    private ChatbotResponse findSimilarResponses(List<String> extractedKeywords) {
        List<ChatbotResponse> allResponses = chatbotResponseRepository.findAll();
        return allResponses.stream()
                .filter(response -> countMatchingKeywords(response, extractedKeywords) > 0)
                .findFirst()
                .orElse(null);
    }

    // 응답과 사용자의 키워드를 비교하여 일치하는 키워드의 수를 세는 메서드
    private int countMatchingKeywords(ChatbotResponse response, List<String> extractedKeywords) {
        List<String> responseKeywords = Arrays.asList(response.getKeywords().split(","));
        return (int) extractedKeywords.stream()
                .filter(keyword -> responseKeywords.stream().anyMatch(k -> k.equalsIgnoreCase(keyword) || k.contains(keyword)))
                .count();
    }

    // 최종적으로 사용자의 입력과 현재 컨텍스트를 바탕으로 응답을 생성하는 메서드
    private String processResponse(ChatbotResponse response, String input, ConversationContext context) {
        String baseResponse = response.getResponseText();

        // 즉각적인 정보 제공을 위한 키워드 처리
        if (input.contains("사료")) {
            return baseResponse + "\n" + getProductInfo("사료");
        } else if (input.contains("간식")) {
            return baseResponse + "\n" + getProductInfo("간식");
        } else if (input.contains("장난감")) {
            return baseResponse + "\n" + getProductInfo("장난감");
        }

        // 추가된 컨텍스트 처리
        switch (response.getNextContextId()) {
            case "specific_product":
                return baseResponse + "\n" + getProductInfo(input);
            case "price_inquiry":
                return baseResponse + "\n" + getPriceInfo(input);
            case "discount_inquiry":
                return baseResponse + "\n" + getDiscountInfo();
            case "popular_products":
                return baseResponse + "\n" + getPopularProducts();
            case "review_inquiry":
                return baseResponse + "\n" + getProductReviews(input);
            case "brand_inquiry":
                return baseResponse + "\n" + getBrandInfo(input);
            case "size_inquiry":
                return baseResponse + "\n" + getSizeRecommendations(input);
            case "restock_inquiry":
                return baseResponse + "\n" + getRestockInfo(input);
            default:
                return baseResponse;
        }
    }

    // 특정 제품 정보를 가져오는 메서드
    private String getProductInfo(String productName) {
        List<ProductEntity> products = productRepository.findByProductNameContaining(productName);
        if (products.isEmpty()) {
            return "죄송합니다. 해당 제품을 찾을 수 없습니다.";
        }
        ProductEntity product = products.get(0);
        return String.format("%s에 대한 정보입니다:\n가격: %d원\n할인가: %d원\n설명: %s",
                product.getProductName(), product.getPrice(), product.getDiscountPrice(), product.getProductDetail());
    }

    // 제품의 가격 정보를 가져오는 메서드
    private String getPriceInfo(String productName) {
        List<ProductEntity> products = productRepository.findByProductNameContaining(productName);
        if (products.isEmpty()) {
            return "죄송합니다. 해당 제품을 찾을 수 없습니다.";
        }
        ProductEntity product = products.get(0);
        return String.format("%s의 가격은 %d원입니다. 현재 할인 중이라면 할인가는 %d원입니다.",
                product.getProductName(), product.getPrice(), product.getDiscountPrice());
    }

    // 할인 중인 제품 목록을 가져오는 메서드
    private String getDiscountInfo() {
        List<ProductEntity> discountedProducts = productRepository.findByDiscountGreaterThan(0L);
        return "현재 할인 중인 상품들입니다:\n" + discountedProducts.stream()
                .limit(5)
                .map(p -> String.format("%s: %d원 (할인가: %d원)", p.getProductName(), p.getPrice(), p.getDiscountPrice()))
                .collect(Collectors.joining("\n"));
    }

    // 인기 제품 목록을 가져오는 메서드
    private String getPopularProducts() {
        List<ProductEntity> popularProducts = productRepository.findTop4ByOrderByProductNoDesc();
        return "최근 추가된 상품들입니다:\n" + popularProducts.stream()
                .map(p -> String.format("%s: %d원", p.getProductName(), p.getPrice()))
                .collect(Collectors.joining("\n"));
    }

    // 제품 리뷰 정보를 가져오는 메서드
    private String getProductReviews(String productName) {
        return productName + "에 대한 리뷰 기능은 현재 개발 중입니다. 빠른 시일 내에 제공하겠습니다.";
    }

    // 특정 브랜드 정보를 가져오는 메서드
    private String getBrandInfo(String brandName) {
        return brandName + " 브랜드의 제품 목록 기능은 현재 개발 중입니다. 빠른 시일 내에 제공하겠습니다.";
    }

    // 사이즈 추천 정보를 가져오는 메서드
    private String getSizeRecommendations(String size) {
        return size + " 크기의 반려동물을 위한 추천 제품 기능은 현재 개발 중입니다. 빠른 시일 내에 제공하겠습니다.";
    }

    // 재입고 정보를 가져오는 메서드
    private String getRestockInfo(String productName) {
        return productName + "의 재입고 정보 확인 기능은 현재 개발 중입니다. 빠른 시일 내에 제공하겠습니다.";
    }

    // 초기 메시지를 반환하는 메서드
    public String getInitialMessage() {
        return "안녕하세요! PetFirst 챗봇입니다. 어떤 도움이 필요하신가요?";
    }
}
