package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.user.service.WildUserService;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
