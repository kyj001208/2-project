package com.green.petfirst.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {
	
	// 엔티티가 생성될 때 자동으로 현재 시간을 설정하는 필드
    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    protected LocalDateTime createdAt; // 엔티티의 생성 일시
    
    // 엔티티가 업데이트될 때 자동으로 현재 시간을 설정하는 필드
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    protected LocalDateTime updatedAt; // 엔티티의 마지막 수정 일시
}
