package ru.otus.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Sergei", authorities = {"ROLE_USER"})
    public void testAuthenticatedOnUser() throws Exception {
        mockMvc.perform(get("/list_genres"))
                .andExpect(status().isOk());
    }
}
