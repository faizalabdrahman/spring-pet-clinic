package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.repositories.OwnerRepository;
import manhar.laziaf.springpetclinic.repositories.PetRepository;
import manhar.laziaf.springpetclinic.repositories.PetTypeRepository;
import manhar.laziaf.springpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerJPAServiceImpl implements OwnerService
{
    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    public OwnerJPAServiceImpl(OwnerRepository ownerRepository, PetTypeRepository petTypeRepository,
                               PetRepository petRepository)
    {
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Set<Owner> findAll()
    {
        Set<Owner> ownerSet = new HashSet<>();

        ownerRepository.findAll().forEach(ownerSet::add);

        return ownerSet;
    }

    @Override
    public Owner findById(Long id)
    {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if(optionalOwner.isPresent())
        {
            return optionalOwner.get();
        }

        return null;
    }

    @Override
    public Owner save(Owner object)
    {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object)
    {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName)
    {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName)
    {
        //todo
        return null;
    }
}
