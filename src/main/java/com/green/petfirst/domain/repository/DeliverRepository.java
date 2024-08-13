package com.green.petfirst.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.DeliverEntity;

@Repository
public interface DeliverRepository extends JpaRepository<DeliverEntity, Long> {

}
