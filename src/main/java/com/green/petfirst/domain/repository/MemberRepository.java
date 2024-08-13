package com.green.petfirst.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.petfirst.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByUserId(String userId);
	
	MemberEntity findByEmail(String email);

	@Query("SELECT COUNT(m) FROM MemberEntity m")
    long countAllMembers();
	
}
