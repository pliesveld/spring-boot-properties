package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan
public class Application implements CommandLineRunner {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        LOG.info("main before run");
        SpringApplication.run(Application.class, args);
        LOG.info("main after run");
    }

    @Override
    public void run(String... args) throws Exception {
        helloService.printProperties();
    }
}
