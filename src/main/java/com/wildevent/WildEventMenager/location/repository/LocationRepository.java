package com.wildevent.WildEventMenager.location.repository;

import com.wildevent.WildEventMenager.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

}
