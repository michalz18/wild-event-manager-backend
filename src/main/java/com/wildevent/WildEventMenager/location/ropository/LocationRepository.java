package com.wildevent.WildEventMenager.location.ropository;

import com.wildevent.WildEventMenager.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
