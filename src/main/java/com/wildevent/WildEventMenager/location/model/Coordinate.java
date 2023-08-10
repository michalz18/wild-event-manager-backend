package com.wildevent.WildEventMenager.location.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @OneToOne(mappedBy = "coordinate")
    private Location location;
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private double coordinateX;
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private double coordinateY;

}
