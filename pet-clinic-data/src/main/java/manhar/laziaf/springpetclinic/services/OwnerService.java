package manhar.laziaf.springpetclinic.services;

import manhar.laziaf.springpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
