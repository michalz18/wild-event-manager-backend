package com.wildevent.WildEventMenager.event.model;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min = 3, max = 70)
    private String title;
    @Lob
    @Column(length = 1000)
    @Size(min = 3, max = 1000)
    private String description;
    @Future
    @NotNull
    private LocalDateTime startsAt;
    @Future
    @NotNull
    private LocalDateTime endsAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
    @NotNull
    private boolean openToPublic;
    @ManyToMany
    private List<WildUser> organizer;
}
