package com.green.petfirst.domain.repository;

import com.green.petfirst.domain.entity.ChatbotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ChatbotResponseRepository 인터페이스는 ChatbotResponse 엔티티에 대한 데이터베이스 작업을 정의합니다.
 * 이 인터페이스는 JpaRepository를 확장하여 기본적인 CRUD 작업을 상속받습니다.
 */
public interface ChatbotResponseRepository extends JpaRepository<ChatbotResponse, Long> {

    /**
     * 주어진 입력과 컨텍스트 ID에 기반하여 관련성 있는 ChatbotResponse 목록을 검색합니다.
     * 결과는 컨텍스트 ID 일치 여부와 키워드 포함 여부에 따라 정렬됩니다.
     *
     * @param input 사용자 입력
     * @param contextId 현재 대화 컨텍스트 ID
     * @return 관련성에 따라 정렬된 ChatbotResponse 목록
     */
    @Query("SELECT cr FROM ChatbotResponse cr WHERE " +
            "(cr.keywords LIKE %:input% OR cr.contextId = :contextId) " +
            "ORDER BY CASE WHEN cr.contextId = :contextId THEN 0 ELSE 1 END, " +
            "CASE WHEN cr.keywords LIKE %:input% THEN 0 ELSE 1 END")
    List<ChatbotResponse> findByKeywordsContainingOrContextIdOrderByRelevance(
            @Param("input") String input,
            @Param("contextId") String contextId
    );

    /**
     * 주어진 입력을 포함하는 키워드를 가진 ChatbotResponse 목록을 검색합니다.
     * 대소문자를 구분하지 않고 검색합니다.
     *
     * @param input 검색할 키워드
     * @return 키워드를 포함하는 ChatbotResponse 목록
     */
    @Query("SELECT cr FROM ChatbotResponse cr WHERE LOWER(cr.keywords) LIKE LOWER(CONCAT('%', :input, '%'))")
    List<ChatbotResponse> findByKeywordsContaining(@Param("input") String input);
}