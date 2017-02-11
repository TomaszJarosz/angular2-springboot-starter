package pl.jarosz.tomasz.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.jarosz.tomasz.PortfolioApplication;
import pl.jarosz.tomasz.dao.UserRepository;
import pl.jarosz.tomasz.dao.entity.User;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by Tomek on 11.02.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortfolioApplication.class)
@WebAppConfiguration
public class UserControllerTest {
    private User user;
    private User user1;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.userRepository.deleteAll();
        this.user = userRepository.save(new User("login1", "firstName1", "lastName1", "email@1.com", "password1"));
        this.user = userRepository.save(new User("login2", "firstName2", "lastName2", "email@2.com", "password2"));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Test
    public void getUserTest() throws Exception {
        //given
        String uri = "/user/get/" + user.getId();
        //when
        mockMvc.perform(get(uri))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));

    }

    @Test
    public void deleteUserTest() throws Exception {
        //given
        String uri = "/user/delete/" + user.getId();
        //when
        mockMvc.perform(delete(uri))
                //then
                .andExpect(status().isOk());
        List<User> users = (List<User>) userRepository.findAll();
        assertTrue(users.size() == 1);

    }

    @Test
    public void createUserTest() throws Exception {
        //given
        User newUser = new User("login3","firstName3","seconName4","email4","password4");
        //when
        mockMvc.perform(post("/user/create").content(json(newUser)).contentType(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(any(int.class))))
                .andExpect(jsonPath("$.login", is(newUser.getLogin())))
                .andExpect(jsonPath("$.firstName", is(newUser.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(newUser.getLastName())))
                .andExpect(jsonPath("$.email", is(newUser.getEmail())))
                .andExpect(jsonPath("$.password", is(newUser.getPassword())));
        List<User> users = (List<User>) userRepository.findAll();
        assertTrue(users.size() == 3);
    }

    @Test
    public void update() throws Exception {
        //given
        final String NEW_EMAIL = "newEmail@email.com";
        String uri = "/user/update/" + user.getId();

        User updatedUser = new User();
        updatedUser.setLogin(user.getLogin());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(NEW_EMAIL);
        updatedUser.setPassword(user.getPassword());

        //when
        mockMvc.perform(put(uri).content(json(updatedUser)).contentType(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(NEW_EMAIL)))
                .andExpect(jsonPath("$.password", is(user.getPassword())));
        List<User> users = (List<User>) userRepository.findAll();
        assertTrue(users.size() == 2);

    }
}
