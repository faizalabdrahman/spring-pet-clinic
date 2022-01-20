package manhar.laziaf.springpetclinic.services;

import manhar.laziaf.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lastName);
}
