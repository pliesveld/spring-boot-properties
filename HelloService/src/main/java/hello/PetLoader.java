package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class PetLoader implements ApplicationListener<ApplicationReadyEvent> {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private PetRepository petRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOG.info("Loading sample data into embedded database");
        Pet pet = new Pet();
        pet.setName("Odie");
        pet.setType("dog");
        pet.setOwner("Doug");
        petRepository.save(pet);

        Pet pet2 = new Pet();
        pet2.setName("Pico");
        pet2.setType("cat");
        petRepository.save(pet2);
    }
}
