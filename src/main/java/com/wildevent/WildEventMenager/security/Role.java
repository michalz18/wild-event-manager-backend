package com.wildevent.WildEventMenager.security;

import java.util.List;

import static com.wildevent.WildEventMenager.security.Access.*;

public enum Role {
    ALMIGHTY(List.of(MY_EVENTS,
            MY_NOTIFICATIONS,
            EVENT_MANAGEMENT,
            NOTIFICATION_MANAGEMENT,
            STAFF_MANAGEMENT,
            MAP_CONFIG));
    public final List<Access> accesses;
    Role(List<Access> accesses) {
        this.accesses = accesses;
    }
}
