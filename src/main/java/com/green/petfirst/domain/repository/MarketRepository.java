package com.green.petfirst.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.MemberEntity;

public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
	
	Optional<MarketEntity> findByMember_MemNo(long memberNo);

	Optional<MarketEntity> findByMember(MemberEntity member);
	
	Optional<MarketEntity> findByMember_email(String email);
	

}
