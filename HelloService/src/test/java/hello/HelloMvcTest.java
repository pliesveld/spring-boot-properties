package hello;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(secure = false)
public class HelloMvcTest {

    @Inject
    private MockMvc mvc;

    @Test
    public void contextLoad() throws Exception {
        assertNotNull(mvc);
    }

    @Test
    public void whenGreeting_shouldMessage() throws Exception {
        mvc.perform(
            get("/greeting").contentType(MediaType.APPLICATION_JSON)
        )
//        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("content", containsString("Hello, World!")));
    }

    @Test
    public void whenGreetingWithName_shouldMessage() throws Exception {
        mvc.perform(
            get("/greeting").contentType(MediaType.APPLICATION_JSON)
            .param("name", "MockMVC")
        )
//        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("content", containsString("Hello, MockMVC!")));
    }
}
