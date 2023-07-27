package com.wildevent.WildEventMenager.location;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.user.WildUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;

    @Lob
    @Column(length = 1000)
    private String description;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Coordinate coordinate;
    @OneToMany(mappedBy = "location")
    private List<Event> events;
    @ManyToMany(mappedBy = "location")
    private List<WildUser> wildUser;

}
