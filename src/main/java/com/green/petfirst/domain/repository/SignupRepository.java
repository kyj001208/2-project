package com.green.petfirst.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.MemberEntity;

public interface SignupRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByUserId(String userId);
}
