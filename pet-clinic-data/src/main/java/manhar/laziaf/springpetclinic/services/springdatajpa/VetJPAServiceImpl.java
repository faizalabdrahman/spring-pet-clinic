package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Vet;
import manhar.laziaf.springpetclinic.repositories.VetRepository;
import manhar.laziaf.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetJPAServiceImpl implements VetService
{
    private final VetRepository vetRepository;

    public VetJPAServiceImpl(VetRepository vetRepository)
    {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll()
    {
        Set<Vet> vetSet = new HashSet<>();

        vetRepository.findAll().forEach(vetSet::add);

        return vetSet;
    }

    @Override
    public Vet findById(Long id)
    {
        Optional<Vet> vetOptional = vetRepository.findById(id);

        if(vetOptional.isPresent())
        {
            return vetOptional.get();
        }

        return null;
    }

    @Override
    public Vet save(Vet object)
    {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object)
    {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        vetRepository.deleteById(id);
    }
}
