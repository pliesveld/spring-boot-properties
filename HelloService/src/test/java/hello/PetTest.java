package hello;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class PetTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(entityManager);
        assertNotNull(petRepository);
    }

    @Test
    public void givenPet_shouldSave() throws Exception {
        Pet pet = new Pet();
        pet.setName("dog");
        pet.setType("dog");
        pet.setOwner("dog");
        assertNotNull(petRepository.save(pet));
    }

    @Test(expected = ConstraintViolationException.class)
    public void givenPet_whenSaveWithNullName_shouldException() throws Exception {
        Pet pet = new Pet();
        pet.setType("cat");
        pet.setOwner("Cat");
        petRepository.save(pet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void givenPet_whenSaveWithEmptyName_shouldException() throws Exception {
        Pet pet = new Pet();
        pet.setName("");
        pet.setType("dog");
        pet.setOwner("Dog");
        petRepository.save(pet);
    }

    @Test
    public void givenPet_NoOwner_shouldSave() throws Exception {
        Pet pet = new Pet();
        pet.setName("Cat");
        pet.setType("cat");
        Pet cat = petRepository.save(pet);
        assertNotNull(cat);
        assertNotNull(cat.getId());
        assertNotNull(cat.getName());
        assertNull(cat.getOwner());
        assertNotNull(cat.getType());
        assertNotNull(petRepository.findOne(cat.getId()));
    }

    @Test(expected = DataAccessException.class)
    public void givenSavedPet_whenSavePetWithSameName_shouldException() throws Exception {
        Pet pet = new Pet();
        pet.setName("dog");
        pet.setType("dog");
        pet.setOwner("dog");
        petRepository.save(pet);

        Pet pet2 = new Pet();
        pet2.setName("dog");
        pet2.setType("dog");
        petRepository.save(pet2);
    }
}
