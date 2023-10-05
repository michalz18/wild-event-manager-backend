package com.wildevent.WildEventMenager.map.model;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.location.model.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double zoom;
    private int bearing;
    @NotNull
    private boolean current;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Coordinate coordinate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Location> locations;

    public Map(Coordinate coordinate, double zoom, int bearing, boolean current) {
        this.zoom = zoom;
        this.bearing = bearing;
        this.current = current;
        this.coordinate = coordinate;
    }

}
