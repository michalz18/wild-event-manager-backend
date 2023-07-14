package com.wildevent.WildEventMenager.user;

import com.wildevent.WildEventMenager.event.Event;
import com.wildevent.WildEventMenager.location.Location;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class WildUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private boolean active;

    @ManyToMany
    private List<Location> locations;

    @ManyToMany(mappedBy = "organizers")
    private List<Event> eventsOrganized;

    @OneToMany(mappedBy = "userProposing")
    private List<Event> eventsProposed;
}
