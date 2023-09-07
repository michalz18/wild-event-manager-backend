package com.wildevent.WildEventMenager.coordinate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
