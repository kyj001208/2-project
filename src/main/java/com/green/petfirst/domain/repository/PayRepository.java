package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.PayEntity;

public interface PayRepository extends JpaRepository<PayEntity, Long>{

}
