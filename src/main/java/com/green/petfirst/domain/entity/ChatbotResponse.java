package com.green.petfirst.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chatbot_responses")
@Data
public class ChatbotResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keywords;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String responseText;

    @Column(name = "function_type")
    private String functionType;
}