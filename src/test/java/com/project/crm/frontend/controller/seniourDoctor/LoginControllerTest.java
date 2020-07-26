package com.project.crm.frontend.controller.seniourDoctor;

import com.project.crm.backend.dto.UserDTO;
import com.project.crm.backend.util.Constants;
import com.project.crm.backend.util.RepoMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest extends RepoMethods {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void login_check_shouldRedirectTo200View() throws Exception {

        this.mockMvc.perform(get("/")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    public void defaultPage_checkWithAdminParam_shouldRedirectTo200View() throws Exception {

        this.mockMvc.perform(get("/default")
                .with(user(Constants.ADMIN_DEV_INN).password(Constants.ADMIN_DEV_PASSWORD).roles(Constants.ADMIN))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("/"));
    }
}
