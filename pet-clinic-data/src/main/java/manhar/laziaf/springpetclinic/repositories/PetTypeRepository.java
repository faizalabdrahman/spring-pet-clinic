package manhar.laziaf.springpetclinic.repositories;

import manhar.laziaf.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long>
{
}
