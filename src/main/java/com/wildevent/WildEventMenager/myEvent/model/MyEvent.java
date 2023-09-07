package com.wildevent.WildEventMenager.myEvent.model;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class MyEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @ManyToOne
    private Location location;

    @NotNull
    private boolean openToPublic;

    @FutureOrPresent
    private LocalDateTime startsAt;

    @FutureOrPresent
    private LocalDateTime endsAt;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private WildUser organizer;
}

