package com.wildevent.WildEventMenager.event.repository;

import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT new com.wildevent.WildEventMenager.dto.EventDTO(e.title, e.startsAt, l.title) " +
            "FROM Event e " +
            "JOIN e.location l " +
            "WHERE e.startsAt >= :now AND e.startsAt <= :end AND e.accepted = true")
    List<EventTitleDTO> findUpcomingEvents(@Param("now") LocalDateTime now, @Param("end") LocalDateTime endOfDay);
}
