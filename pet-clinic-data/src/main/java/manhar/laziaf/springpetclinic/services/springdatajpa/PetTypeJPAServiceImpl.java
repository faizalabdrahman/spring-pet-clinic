package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.PetType;
import manhar.laziaf.springpetclinic.repositories.PetTypeRepository;
import manhar.laziaf.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeJPAServiceImpl implements PetTypeService
{
    private final PetTypeRepository petTypeRepository;

    public PetTypeJPAServiceImpl(PetTypeRepository petTypeRepository)
    {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll()
    {
        Set<PetType> petTypeSet = new HashSet<>();

        petTypeRepository.findAll().forEach(petTypeSet::add);

        return petTypeSet;
    }

    @Override
    public PetType findById(Long id)
    {
        Optional<PetType> petTypeOptional = petTypeRepository.findById(id);

        if(petTypeOptional.isPresent())
        {
            return petTypeOptional.get();
        }

        return null;
    }

    @Override
    public PetType save(PetType object)
    {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object)
    {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        petTypeRepository.deleteById(id);
    }
}
