package com.wildevent.WildEventMenager.user.model;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.role.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WildUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String password;
    @NotNull
    @Size(min = 3, max = 40)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{9}$", message = "Invalid phone number format")
    private String phone;
    @NotNull
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Location> location;
    @ManyToMany(mappedBy = "organizer")
    private List<Event> eventOrganized;

}
