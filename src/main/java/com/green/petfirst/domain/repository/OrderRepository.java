package com.green.petfirst.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.petfirst.domain.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findAllByMemberMemNo(long memNo);

	@Query("SELECT o.product.productName AS productName, SUM(o.quantity) AS quantity " +
	           "FROM OrderEntity o " +
	           "GROUP BY o.product.productName")
	    List<Object[]> findProductQuantities();
}
