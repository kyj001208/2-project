package com.green.petfirst.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.petfirst.domain.entity.cs.FaqDivision;
import com.green.petfirst.domain.entity.cs.FaqEntity;


public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

	List<FaqEntity> findByDivision(FaqDivision faqDivision);

}
