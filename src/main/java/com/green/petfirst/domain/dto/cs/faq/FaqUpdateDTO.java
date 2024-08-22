package com.green.petfirst.domain.dto.cs.faq;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter//Controller 에서 부트가 파라미터데이터()를 매핑
public class FaqUpdateDTO {
	
	private Long no;          		// Faq 번호
    private String title;           // Faq 제목
    private String content;         // Faq 내용
	
}
