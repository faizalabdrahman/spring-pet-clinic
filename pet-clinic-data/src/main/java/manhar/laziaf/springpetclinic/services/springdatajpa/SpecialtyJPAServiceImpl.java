package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Specialty;
import manhar.laziaf.springpetclinic.repositories.SpecialtyRepository;
import manhar.laziaf.springpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtyJPAServiceImpl implements SpecialtyService
{
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyJPAServiceImpl(SpecialtyRepository specialtyRepository)
    {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll()
    {
        Set<Specialty> specialtySet = new HashSet<>();

        specialtyRepository.findAll().forEach(specialtySet::add);

        return specialtySet;
    }

    @Override
    public Specialty findById(Long id)
    {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(id);

        if(optionalSpecialty.isPresent())
        {
            return optionalSpecialty.get();
        }

        return null;
    }

    @Override
    public Specialty save(Specialty object)
    {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object)
    {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        specialtyRepository.deleteById(id);
    }
}
