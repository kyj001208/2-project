package com.green.petfirst.domain.entity.cs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.green.petfirst.domain.dto.cs.notice.NoticeDetailDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeListDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeUpdateDTO;
import com.green.petfirst.domain.entity.BaseEntity;

@DynamicUpdate // 변경된 필드만 업데이트하는 기능을 제공하는 Hibernate의 애노테이션
@Getter // 모든 필드에 대해 getter 메서드를 자동 생성
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 지원
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 포함하는 생성자를 생성하며, 접근 레벨을 PRIVATE로 설정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@Entity // 이 클래스가 JPA 엔티티임을 명시
@Table(name = "notice") // 데이터베이스의 'notice' 테이블과 매핑
public class NoticeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private Long no;

    @Column(nullable = false) // null 값을 허용하지 않는 'title' 컬럼
    private String title;

    @Column(nullable = false) // null 값을 허용하지 않는 'content' 컬럼
    private String content;
    
    
    /**
     * 엔티티를 공지사항 목록 DTO로 변환하는 메서드
     *
     * @return NoticeListDTO 엔티티의 필드를 사용하여 생성된 공지사항 목록 DTO
     */
    public NoticeListDTO toNoticeListDTO() {
    	return NoticeListDTO.builder()
    			.no(no)
    			.title(title)
    			.createdAt(createdAt)
    			.updatedAt(updatedAt)
    			.build();
    }
    
    /**
     * 엔티티를 공지사항 상세 DTO로 변환하는 메서드
     *
     * @return NoticeDetailDTO 엔티티의 필드를 사용하여 생성된 공지사항 상세 DTO
     */
    public NoticeDetailDTO toDetailDTO() {
    	return NoticeDetailDTO.builder()
    			.no(no)
    			.title(title)
    			.content(content)
    			.createdAt(createdAt)
    			.updatedAt(updatedAt)
    			.build();
    }

    /**
     * 주어진 DTO의 데이터를 사용하여 현재 엔티티를 업데이트하는 메서드
     *
     * @param dto 공지사항의 업데이트 정보를 담고 있는 DTO
     * @return 업데이트된 NoticeEntity 객체
     */
  	public NoticeEntity update(NoticeUpdateDTO dto) {
  		// 제목과 내용을 DTO로부터 가져와서 엔티티에 반영
  		this.title = dto.getTitle();
  		this.content = dto.getContent();
  		return this;
  	}

	
}
