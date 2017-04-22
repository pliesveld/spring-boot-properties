package hello;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.inject.Inject;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(secure = false)
public class HelloMvcTest {

    @Inject
    private MockMvc mvc;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

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
    @Parameters({
        "name, 200",
        "MVC, 200",
        "swearWord, 200"
    })
    public void whenGreetingWithName_shouldMessage(String name, int expectedStatus) throws Exception {
        mvc.perform(
            get("/greeting").contentType(MediaType.APPLICATION_JSON)
            .param("name", name)
        )
        .andExpect(status().is(expectedStatus))
        .andExpect(jsonPath("content", stringContainsInOrder(Arrays.asList("Hello, ", name, "!"))));
    }
}
