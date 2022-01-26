package manhar.laziaf.springpetclinic.repositories;

import manhar.laziaf.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long>
{
}
