package com.green.petfirst.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.DeliverEntity;

@Repository
public interface DeliverRepository extends JpaRepository<DeliverEntity, Long> {

	@Query("SELECT DISTINCT d.devCompany FROM DeliverEntity d")
    List<String> findDistinctCompanies();
}
