package com.wildevent.WildEventMenager.user.repository;

import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WildUserRepository extends JpaRepository<WildUser, UUID> {
}
