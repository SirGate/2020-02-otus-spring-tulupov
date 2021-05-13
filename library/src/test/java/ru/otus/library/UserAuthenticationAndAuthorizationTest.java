package ru.otus.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.service.AuthorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Sergei", roles = {"USER"})
public class UserAuthenticationAndAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AuthorService authorService;

    @Test
    public void testAuthenticatedOnUser() throws Exception {
        mockMvc.perform(get("/list_genres"))
                .andExpect(status().isOk());
    }

    @Test(expected = AccessDeniedException.class)
    public void givenRoleUser_whenCallDeleteAuthor_thenAccessDenied() {
        authorService.deleteAuthor("");
    }
}
