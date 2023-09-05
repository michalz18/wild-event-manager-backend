package com.wildevent.WildEventMenager.map.model;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.location.model.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double zoom;
    private int bearing;
    @NotNull
    private boolean current;
    @OneToOne(fetch = FetchType.EAGER)
    private Coordinate coordinate;
    @ManyToMany
    private List<Location> locations;

}
