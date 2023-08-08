package com.wildevent.WildEventMenager.event.repository;

import com.wildevent.WildEventMenager.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByAcceptedTrueAndStartsAtBetweenOrderByStartsAtAsc(LocalDateTime now, LocalDateTime endOfDay);
}
