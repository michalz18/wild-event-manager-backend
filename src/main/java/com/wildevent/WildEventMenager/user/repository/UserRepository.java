package com.wildevent.WildEventMenager.user.repository;

import com.wildevent.WildEventMenager.user.WildUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<WildUser, UUID> {
}
