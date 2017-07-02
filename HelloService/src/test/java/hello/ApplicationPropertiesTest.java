package hello;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;

@SpringBootTest(classes = Application.class)
public class ApplicationPropertiesTest {
    @Test
    public void givenDefaultProfile_whenBoot_shouldLoad() throws Exception {
    }
}
