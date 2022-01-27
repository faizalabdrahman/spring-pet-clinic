package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Visit;
import manhar.laziaf.springpetclinic.repositories.VisitRepository;
import manhar.laziaf.springpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitJPAServiceImpl implements VisitService
{
    private final VisitRepository visitRepository;

    public VisitJPAServiceImpl(VisitRepository visitRepository)
    {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll()
    {
        Set<Visit> visitSet = new HashSet<>();

        visitRepository.findAll().forEach(visitSet::add);

        return visitSet;
    }

    @Override
    public Visit findById(Long id)
    {
        Optional<Visit> visitOptional = visitRepository.findById(id);

        if(visitOptional.isPresent())
        {
            return visitOptional.get();
        }

        return null;
    }

    @Override
    public Visit save(Visit object)
    {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object)
    {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        visitRepository.deleteById(id);
    }
}
