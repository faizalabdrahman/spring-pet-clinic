package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.services.PetService;

import java.util.Set;

public class PetServiceMapImpl extends AbstractServiceMap<Pet, Long> implements PetService
{
    @Override
    public Set<Pet> findAll()
    {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object)
    {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
