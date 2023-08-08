package com.wildevent.WildEventMenager.location.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(mappedBy = "coordinate")
    private Location location;
    private double coordinateX;
    private double coordinateY;

}
