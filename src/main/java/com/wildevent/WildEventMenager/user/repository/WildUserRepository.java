package com.wildevent.WildEventMenager.user.repository;

import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface WildUserRepository extends JpaRepository<WildUser, UUID> {

    @Modifying
    @Query("UPDATE WildUser w SET w.active = false WHERE w.id = ?1")
    void deactivateUser(UUID userId);
}
