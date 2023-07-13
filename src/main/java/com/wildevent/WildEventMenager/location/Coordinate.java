package com.wildevent.WildEventMenager.location;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "coordinate")
    private Location location;
    private int coordinateXOnMap;
    private int coordinateYOnMap;

}
