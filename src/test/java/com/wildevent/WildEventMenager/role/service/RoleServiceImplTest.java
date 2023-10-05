package com.wildevent.WildEventMenager.role.service;

import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;
    @Mock
    private RoleRepository roleRepository;

    @Test
    void testGetAllActiveUsers() {
        List<Role> expectedRoles = Arrays.asList(new Role(), new Role());
        Mockito.when(roleRepository.findAll()).thenReturn(expectedRoles);

        List<Role> actualRoles = roleService.getAllRoles();

        assertEquals(expectedRoles, actualRoles);
    }

}