package com.green.petfirst.domain.entity.cs;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.domain.dto.cs.faq.FaqDetailDTO;
import com.green.petfirst.domain.dto.cs.faq.FaqUpdateDTO;
import com.green.petfirst.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FaqEntity 클래스는 FAQ 정보를 데이터베이스에 저장하기 위한 JPA 엔티티입니다.
 * Lombok 애너테이션을 사용하여 getter, setter, 생성자, 빌더 패턴을 자동으로 생성합니다.
 */
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faq") 
public class FaqEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long faqNo; 
    
    @Enumerated(EnumType.STRING)//DB에저장시 문자형식으로
    @Column(nullable = false)
    private FaqDivision division;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)  
    private String content;
    
    
    public FaqDetailDTO toFaqDetailDTO() {
    	return FaqDetailDTO.builder()
    			.faqNo(faqNo)
    			.division(division.koName())
    			.title(title)
    			.content(content)
    			.createdAt(createdAt)
    			.updatedAt(updatedAt)
    			.build();
    }
	
    public FaqEntity update(FaqUpdateDTO dto) {
    	this.title = dto.getTitle();
  		this.content = dto.getContent();
  		return this;
    }
    
}
