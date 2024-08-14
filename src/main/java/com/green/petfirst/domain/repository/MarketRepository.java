package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.MarketEntity;

public interface MarketRepository extends JpaRepository<MarketEntity, Long> {

}
