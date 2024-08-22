package com.green.petfirst.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * ChatbotResponse 엔티티는 챗봇의 응답 데이터를 표현합니다.
 * 이 엔티티는 데이터베이스의 'chatbot_responses' 테이블과 매핑됩니다.
 */
@Entity
@Table(name = "chatbot_responses")
@Data
public class ChatbotResponse {
    /**
     * 응답의 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 응답과 연관된 키워드들. 여러 키워드는 쉼표로 구분될 수 있습니다.
     */
    @Column(nullable = false)
    private String keywords;

    /**
     * 실제 응답 텍스트. 대량의 텍스트를 저장할 수 있도록 TEXT 타입으로 정의됩니다.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String responseText;

    /**
     * 현재 대화 컨텍스트의 ID
     */
    @Column(name = "context_id")
    private String contextId;

    /**
     * 다음 대화 단계의 컨텍스트 ID
     */
    @Column(name = "next_context_id")
    private String nextContextId;
}