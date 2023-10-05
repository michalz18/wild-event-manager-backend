package com.wildevent.WildEventMenager.location.model;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.user.model.WildUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @Lob
    @Column(length = 1000)
    @Size(min = 3, max = 1_000)
    private String description;
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Coordinate coordinate;
    @OneToMany(mappedBy = "location")
    private List<Event> events;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "location")
    private List<WildUser> wildUser;
    @ManyToOne
    private Map map;

    public Location(String title, String description, Coordinate coordinate, List<WildUser> wildUser, Map map) {
        this.title = title;
        this.description = description;
        this.coordinate = coordinate;
        this.wildUser = wildUser;
        this.map = map;
    }
}
