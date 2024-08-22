package com.green.petfirst.domain.dto.cs.notice;

import com.green.petfirst.domain.entity.cs.NoticeEntity;

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
public class NoticeSaveDTO {

    private String title;             // 공지사항 제목
    private String content;           // 공지사항 내용
    
    /**
     * NoticeSaveDTO 객체를 NoticeEntity 객체로 변환하는 메서드
     *
     * @return NoticeEntity 변환된 NoticeEntity 객체
     */
    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .title(this.title)          // DTO의 제목을 엔티티에 설정
                .content(this.content)      // DTO의 내용을 엔티티에 설정
                .build();
    }
}
