package com.wildevent.WildEventMenager.map.repository;

import com.wildevent.WildEventMenager.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    Map findFirstByCurrentIsTrue();

    @Modifying
    @Query("UPDATE Map m SET m.current = false WHERE m.id = :id")
    void updateCurrentToFalse(@Param("id") UUID id);

}
