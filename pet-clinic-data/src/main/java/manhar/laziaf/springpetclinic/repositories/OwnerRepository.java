package manhar.laziaf.springpetclinic.repositories;

import manhar.laziaf.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{
}
