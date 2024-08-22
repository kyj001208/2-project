package com.green.petfirst.domain.dto.cs.qna;

import com.green.petfirst.domain.entity.cs.QnaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 모든 필드에 대한 getter 메서드를 자동 생성
@Setter // 모든 필드에 대한 setter 메서드를 자동 생성
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 지원
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 생성
public class QnaSaveDTO {
	
	  private String title;             // Q&A 제목
	  private String content;           // Q&A 내용
	  
	  public QnaEntity toEntity() {
		  return QnaEntity.builder()
				  .title(this.title)
				  .content(this.content)
				  .build();
	  }
}
