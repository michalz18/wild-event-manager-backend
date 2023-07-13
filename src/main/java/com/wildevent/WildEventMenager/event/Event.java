package com.wildevent.WildEventMenager.event;

import com.wildevent.WildEventMenager.location.Location;
import com.wildevent.WildEventMenager.notification.Notification;
import com.wildevent.WildEventMenager.user.WildUser;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    @ManyToOne
    private Location location;

    private boolean accepted;

    @ManyToOne
    private WildUser userProposing;

    @ManyToMany
    private List<WildUser> organizers;

    @OneToMany(mappedBy = "eventItAppliesTo")
    private List<Notification> notifications;

}
