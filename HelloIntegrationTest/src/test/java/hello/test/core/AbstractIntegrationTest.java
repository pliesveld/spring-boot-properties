package hello.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;
import java.io.InputStream;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import hello.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, RestAssuredTestExecutionListener.class})
public class AbstractIntegrationTest {
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    protected EmbeddedWebApplicationContext server;

    @LocalServerPort
    protected int port;

    @Test
    public void whenContextLoad_thenCorrect() throws Exception {
        LOG.debug("Listening on port: {}", port);
        assertEquals(port, RestAssured.port);
    }

    protected String loadJson(String s) {
        InputStream resource = getClass().getResourceAsStream(s);
        assertNotNull(resource);
        final int BUFFER_SIZE = 4096 * 8;
        byte[] bytes = new byte[BUFFER_SIZE];
        try {
            int bread = resource.read(bytes);
            bytes[bread] = '\0';
            assert bread != BUFFER_SIZE;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: "+ s, e);
        }
        return new String(bytes);
    }
}
