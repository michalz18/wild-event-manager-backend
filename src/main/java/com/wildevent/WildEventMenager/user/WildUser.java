package com.wildevent.WildEventMenager.user;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Entity
public class WildUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min = 3, max = 40)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{9}$", message = "Invalid phone number format")
    private String phone;
    @NotNull
    private boolean active;
    @ManyToMany
    private List<Location> location;
    @ManyToMany(mappedBy = "organizer")
    private List<Event> eventOrganized;
}
