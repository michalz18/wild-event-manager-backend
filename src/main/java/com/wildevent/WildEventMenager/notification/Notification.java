package com.wildevent.WildEventMenager.notification;

import com.wildevent.WildEventMenager.event.Event;
import com.wildevent.WildEventMenager.location.Location;
import com.wildevent.WildEventMenager.user.WildUser;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private NotificationType notificationType;

    @ManyToOne
    private Location locationItAppliesTo;

    @ManyToOne
    private Event eventItAppliesTo;

    @ManyToMany
    private List<WildUser> responsibleUsers;

    @ManyToOne
    private WildUser lastUserToChange;

    private boolean archived;

}
