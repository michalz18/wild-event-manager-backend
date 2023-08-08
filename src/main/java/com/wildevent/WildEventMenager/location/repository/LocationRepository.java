package com.wildevent.WildEventMenager.location.repository;

import com.wildevent.WildEventMenager.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    @Query("SELECT l FROM Location l LEFT JOIN Event e " +
            "ON  l.id = e.location.id AND e.startsAt BETWEEN :startDate AND :endDate " +
            "WHERE l.id = :locationId")
    Optional<Location> findLocationWithEventsBetweenDates(@Param("locationId") UUID locationId,
                                                          @Param("startDate") LocalDateTime startDate,
                                                          @Param("endDate") LocalDateTime endDate);
}
