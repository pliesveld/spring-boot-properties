package hello;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Optional;

@Service
public class HistoryService implements ApplicationListener<GreetingEvent> {
    final static private Logger LOG = LogManager.getLogger();

    private ArrayDeque<Greeting> greetings = new ArrayDeque<>(100);

    @Override
    public void onApplicationEvent(GreetingEvent event) {

        if (greetings.size() >= 99) {
            Greeting evited = greetings.pop();
            LOG.debug("Array is full; removing: {}", evited);
        }
        LOG.debug("Adding Greeting event: {}", event.getGreeting());
        greetings.push(event.getGreeting());
    }

    public Optional<Greeting> findGreetingById(final long id) {
        return greetings.stream().filter(greeting -> greeting.getId() == id).findFirst();
    }
}
