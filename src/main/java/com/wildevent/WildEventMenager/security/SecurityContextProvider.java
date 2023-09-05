package com.wildevent.WildEventMenager.security;

import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityContextProvider {

    public UUID getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof WildUser) {
            WildUser loggedInUser = (WildUser) authentication.getPrincipal();
            return loggedInUser.getId();
        }

        return null;
    }
}
