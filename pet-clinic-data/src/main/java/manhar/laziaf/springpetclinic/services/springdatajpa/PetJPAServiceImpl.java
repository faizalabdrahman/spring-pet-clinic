package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.repositories.PetRepository;
import manhar.laziaf.springpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetJPAServiceImpl implements PetService
{
    private final PetRepository petRepository;

    public PetJPAServiceImpl(PetRepository petRepository)
    {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll()
    {
        Set<Pet> petSet = new HashSet<>();

        petRepository.findAll().forEach(petSet::add);

        return petSet;
    }

    @Override
    public Pet findById(Long id)
    {
        Optional<Pet> petOptional = petRepository.findById(id);

        if(petOptional.isPresent())
        {
            return petOptional.get();
        }

        return null;
    }

    @Override
    public Pet save(Pet object)
    {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object)
    {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        petRepository.deleteById(id);
    }
}
