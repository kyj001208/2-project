package com.green.petfirst.domain.repository;

import com.green.petfirst.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
