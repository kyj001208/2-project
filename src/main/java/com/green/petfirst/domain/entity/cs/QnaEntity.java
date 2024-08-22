package com.green.petfirst.domain.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.domain.dto.cs.qna.QnaDetailDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaListDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaUpdateDTO;
import com.green.petfirst.domain.entity.BaseEntity;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "qna")
public class QnaEntity extends BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaNo;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;


    
    public QnaListDTO toQnaListDTO() {
    	return QnaListDTO.builder()
    			.qnaNo(qnaNo)
    			.title(title)
    			.createdAt(createdAt)
    			.updatedAt(updatedAt)
    			.build();
    }
    
    public QnaDetailDTO toDetailDTO() {
    	return QnaDetailDTO.builder()
    			.qnaNo(qnaNo)
    			.title(title)
    			.content(content)
    			.createdAt(createdAt)
    			.updatedAt(updatedAt)
    			.build();
    }
    
    public QnaEntity update(QnaUpdateDTO dto) {
    	this.title = dto.getTitle();
    	this.content = dto.getContent();
    	return this;
    }
}
