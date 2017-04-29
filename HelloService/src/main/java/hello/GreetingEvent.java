package hello;

import org.springframework.context.ApplicationEvent;

public class GreetingEvent extends ApplicationEvent {

    public GreetingEvent(Object source) {
        super(source);
    }

    public Greeting getGreeting() {
        return (Greeting) this.getSource();
    }
}
