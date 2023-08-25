package com.wildevent.WildEventMenager.myEvent.repository;

import com.wildevent.WildEventMenager.myEvent.model.MyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MyEventRepository extends JpaRepository<MyEvent, UUID> {
    @Query("SELECT DISTINCT e FROM MyEvent e " +
            "WHERE e.location IN (SELECT l FROM Location l JOIN l.wildUser u WHERE u.id = :loggedInUserId) " +
            "OR e.organizer.id = :loggedInUserId")
    List<MyEvent> findDistinctByLocationWildUserIdOrOrganizerId(@Param("loggedInUserId") UUID loggedInUserId);
}

