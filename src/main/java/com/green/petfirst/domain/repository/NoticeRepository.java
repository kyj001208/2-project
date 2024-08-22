package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.cs.NoticeEntity;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>{
    // NoticeEntity와 관련된 데이터베이스 작업을 처리하는 리포지토리 인터페이스
    // JpaRepository 인터페이스를 상속받아 기본적인 CRUD 메서드를 제공
    // NoticeEntity는 관리할 엔티티 클래스이며, Long은 해당 엔티티의 기본 키 타입
}
