package com.green.petfirst.controller.chatbot;

import lombok.Data;

/**
 * ConversationContext 클래스는 대화의 현재 상태를 나타냅니다.
 * 이 클래스는 대화의 마지막 컨텍스트 ID와 대화의 깊이를 추적합니다.
 */
@Data
public class ConversationContext {
    /**
     * 마지막으로 처리된 대화 컨텍스트의 ID
     */
    private String lastContextId;

    /**
     * 현재 대화의 깊이 (대화 중 주고받은 메시지의 수)
     */
    private int conversationDepth;

    /**
     * ConversationContext의 기본 생성자
     * 대화 깊이를 0으로 초기화합니다.
     */
    public ConversationContext() {
        this.conversationDepth = 0;
    }
}