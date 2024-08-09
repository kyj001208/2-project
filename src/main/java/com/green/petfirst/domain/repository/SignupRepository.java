package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.login.SignupEntity;

public interface SignupRepository extends JpaRepository<SignupEntity, Long> {

}
