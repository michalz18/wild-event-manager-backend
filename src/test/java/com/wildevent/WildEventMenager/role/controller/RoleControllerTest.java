package com.wildevent.WildEventMenager.role.controller;

import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
class RoleControllerTest {
    String AUTH_ROLES_URL = "/auth/roles";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    void testGetAllRolesAndReturns200() throws Exception {
        List<Role> expectedRoles = Arrays.asList(new Role(), new Role());
        Mockito.when(roleService.getAllRoles()).thenReturn(expectedRoles);

        mockMvc.perform(MockMvcRequestBuilders.get(AUTH_ROLES_URL))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));
    }
}