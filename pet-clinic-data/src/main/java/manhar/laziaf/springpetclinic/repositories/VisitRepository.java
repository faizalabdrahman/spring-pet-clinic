package manhar.laziaf.springpetclinic.repositories;

import manhar.laziaf.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long>
{
}
