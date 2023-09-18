package com.wildevent.WildEventMenager.event.repository;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(LocalDateTime now, LocalDateTime endOfDay);

    @Modifying
    @Query("DELETE FROM Event e WHERE e.location = :location")
    void deleteAllEventsFromThisLocation(Location location);
}
