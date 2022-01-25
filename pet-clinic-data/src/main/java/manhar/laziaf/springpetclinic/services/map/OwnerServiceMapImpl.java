package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.services.OwnerService;
import manhar.laziaf.springpetclinic.services.PetService;
import manhar.laziaf.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMapImpl extends AbstractServiceMap<Owner, Long> implements OwnerService
{

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMapImpl(PetTypeService petTypeService, PetService petService)
    {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll()
    {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object)
    {
        Owner savedOwner = null;

        if(object != null)
        {
            if(object.getPetSet() != null)
            {
                object.getPetSet().forEach(pet ->
                {
                    if(pet.getPetType() != null)
                    {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                    else
                    {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() != null)
                    {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void delete(Owner object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName)
    {
        return null;
    }
}
