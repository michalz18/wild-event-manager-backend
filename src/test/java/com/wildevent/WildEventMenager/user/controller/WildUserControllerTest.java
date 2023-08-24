package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.service.WildUserService;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WildUserController.class)
class WildUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WildUserService wildUserService;

    @Test
    void addUserShouldCreateUserAndReturnCreated() throws Exception {
        String userJson = "{\"name\":\"John\",\"email\":\"john@example.com\",\"phone\":\"123456789\",\"roleIds\":[\"role1\"]}";

        when(wildUserService.createUser(any(ReceivedWildUserDTO.class))).thenReturn(true);

        String STAFF_MANAGEMENT_ACTIVE_STAFF_URL = "/staff-management/staff";
        mockMvc.perform(post(STAFF_MANAGEMENT_ACTIVE_STAFF_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated());

    }

    @Test
    void shouldUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        String userJson = "{\"name\":\"John\",\"email\":\"john@example.com\",\"phone\":\"123456789\",\"roleIds\":[\"role1\"]}";

        String STAFF_MANAGEMENT_ACTIVE_STAFF_URL = "/staff-management/staff/";
        mockMvc.perform(put(STAFF_MANAGEMENT_ACTIVE_STAFF_URL + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());

        verify(wildUserService, times(1)).updateUser(eq(userId), any(ReceivedWildUserDTO.class));
    }

    @Test
    public void testProperStatusAfterSuccessfulDeactivatingUser() throws Exception {
        UUID userId = UUID.randomUUID();

        doNothing().when(wildUserService).deactivateUser(userId);

        String STAFF_MANAGEMENT_ACTIVE_STAFF_URL = "/staff-management/staff/";
        mockMvc.perform(put(STAFF_MANAGEMENT_ACTIVE_STAFF_URL + "/deactivate/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User deactivated successfully"));
    }

    @Test
    public void testProperStatusAfterNotFindingSpecificUser() throws Exception {
        UUID userId = UUID.randomUUID();
        String errorMessage = "User not found";

        doThrow(new RuntimeException(errorMessage)).when(wildUserService).deactivateUser(userId);

        String STAFF_MANAGEMENT_ACTIVE_STAFF_URL = "/staff-management/staff/";
        mockMvc.perform(put(STAFF_MANAGEMENT_ACTIVE_STAFF_URL + "/deactivate/" + userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string(errorMessage));
    }
}
