package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    @Query("select u from Pet u")
    Stream<Pet> findAllAsStream();
}
