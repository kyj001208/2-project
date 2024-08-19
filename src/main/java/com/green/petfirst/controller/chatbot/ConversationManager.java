package com.green.petfirst.controller.chatbot;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConversationManager 클래스는 여러 사용자의 대화 컨텍스트를 관리합니다.
 * 이 클래스는 세션 ID를 키로 사용하여 각 사용자의 ConversationContext를 저장하고 검색합니다.
 */
@Component
public class ConversationManager {
    /**
     * 세션 ID를 키로 사용하고 ConversationContext를 값으로 사용하는 동시성 해시맵
     */
    private final Map<String, ConversationContext> conversations = new ConcurrentHashMap<>();

    /**
     * 지정된 세션 ID에 대한 대화 컨텍스트를 설정합니다.
     *
     * @param sessionId 사용자의 세션 ID
     * @param context 설정할 ConversationContext 객체
     */
    public void setContext(String sessionId, ConversationContext context) {
        conversations.put(sessionId, context);
    }

    /**
     * 지정된 세션 ID에 대한 대화 컨텍스트를 검색합니다.
     *
     * @param sessionId 사용자의 세션 ID
     * @return 해당 세션 ID의 ConversationContext, 없으면 null
     */
    public ConversationContext getContext(String sessionId) {
        return conversations.get(sessionId);
    }

    /**
     * 지정된 세션 ID에 대한 대화 컨텍스트를 제거합니다.
     *
     * @param sessionId 제거할 대화 컨텍스트의 세션 ID
     */
    public void clearContext(String sessionId) {
        conversations.remove(sessionId);
    }
}