package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Transactional
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public ResponseEntity<List<Pet>> findAllPets() {
        List<Pet> pets = petRepository.findAllAsStream().collect(Collectors.toList());
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> savePet(@RequestBody Pet pet) {
        if (pet.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(petRepository.save(pet));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable("id") long id) {
        if (!petRepository.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        petRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") long id) {
        Pet pet = petRepository.findOne(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") long id, @RequestBody Pet newPet) {
        Pet pet = petRepository.findOne(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        pet.setName(newPet.getName());
        pet.setOwner(newPet.getOwner());
        pet.setType(newPet.getType());
        pet = petRepository.save(pet);  //TODO: test without this
        return ResponseEntity.ok(pet);
    }
}
