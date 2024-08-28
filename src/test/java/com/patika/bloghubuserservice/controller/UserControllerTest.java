package com.patika.bloghubuserservice.controller;

import com.patika.bloghubuserservice.dto.response.GenericResponseConstants;
import com.patika.bloghubuserservice.dto.response.UserResponse;
import com.patika.bloghubuserservice.model.enums.StatusType;
import com.patika.bloghubuserservice.model.enums.UserType;
import com.patika.bloghubuserservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    void getUserByEmail() throws Exception {
        //given
        Mockito.when(userService.getUserByEmail("cem@gmail.com"))
                .thenReturn(prepareUserResponse());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/users/cem@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(GenericResponseConstants.SUCCESS))
                .andExpect(jsonPath("$.data.email").value("cemdirman@gmail.com"))
                .andExpect(jsonPath("$.data.userId").value("1"));

        //then -> assertion
        resultActions.andExpect(status().isOk());
        verify(userService, times(1)).getUserByEmail("cem@gmail.com");
    }

    @Test
    void changeStatus() throws Exception {

        //given

        //when
        mockMvc.perform(put("/api/v1/users/cem@gmail.com")
                        .param("statusType", StatusType.APPROVED.toString()))
                .andExpect(status().isOk());

        //then
        Mockito.verify(userService).changeStatus("cem@gmail.com", StatusType.APPROVED);

    }

    private UserResponse prepareUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserType(UserType.STANDARD);
        userResponse.setEmail("cemdirman@gmail.com");
        userResponse.setBio("bio");
        userResponse.setUserId(1L);
        return userResponse;
    }
}