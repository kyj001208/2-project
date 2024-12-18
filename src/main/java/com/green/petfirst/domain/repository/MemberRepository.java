package com.green.petfirst.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	

	Optional<MemberEntity> findByUserId(String userId);

	@Query("SELECT COUNT(m) FROM MemberEntity m")
    long countAllMembers();


	
	Optional<MemberEntity> findByEmail(String email);
}
