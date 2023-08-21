package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WildUserServiceImplTest {
    @InjectMocks
    private WildUserServiceImpl wildUserService;
    @Mock
    WildUserRepository wildUserRepository;

    public WildUserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

}