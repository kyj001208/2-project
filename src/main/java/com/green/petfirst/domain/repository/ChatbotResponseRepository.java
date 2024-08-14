package com.green.petfirst.domain.repository;

import com.green.petfirst.domain.entity.ChatbotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatbotResponseRepository extends JpaRepository<ChatbotResponse, Long> {

    @Query("SELECT cr FROM ChatbotResponse cr WHERE :input LIKE CONCAT('%', cr.keywords, '%')")
    List<ChatbotResponse> findByKeywordsContainedInInput(@Param("input") String input);
}
