package hello;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        final long count = counter.getAndIncrement();
        final Greeting greeting = new Greeting(count, String.format(template, name));
        eventPublisher.publishEvent(new GreetingEvent(greeting));
        return ResponseEntity.ok(greeting);
    }

    @RequestMapping("/greeting/id/{id}")
    public ResponseEntity<Greeting> greeting(@PathVariable("id") long id) {
        final Optional<Greeting> greeting = historyService.findGreetingById(id);
        if (!greeting.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(greeting.get());
    }
}
