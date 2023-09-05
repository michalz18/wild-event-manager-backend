package com.wildevent.WildEventMenager.map.repository;

import com.wildevent.WildEventMenager.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    Map findFirstByCurrentIsTrue();
}
