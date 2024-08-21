package com.green.petfirst.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.CartProductEntity;
import com.green.petfirst.domain.entity.MarketEntity;
import com.green.petfirst.domain.entity.ProductEntity;

public interface CartRepository extends JpaRepository<CartProductEntity, Long>{

	

	Optional<ProductEntity> findByMarketMarketNo(Long marketNo);

	List<CartProductEntity> findByMarket_MarketNo(long marketNo);

	void deleteByMarket_MarketNo(long marketNo); //이것도 전체 초기화 (삭제 예정)
	
	//void deleteByMarket_MarketNo(Long marketNo);
	

	

	

	
	}


