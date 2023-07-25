package com.wildevent.WildEventMenager.event;


import com.wildevent.WildEventMenager.location.Location;
import com.wildevent.WildEventMenager.user.WildUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String title;


    @Lob
    @Column(length = 1000)
    private String description;


    private LocalDateTime startsAt;
    private LocalDateTime endsAt;


    @ManyToOne
    private Location location;


    private boolean accepted;


    @ManyToOne
    private WildUser userProposing;


    @ManyToMany
    private List<WildUser> organizer;


}
