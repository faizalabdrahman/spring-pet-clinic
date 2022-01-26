package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Visit;
import manhar.laziaf.springpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitServiceMapImpl extends AbstractServiceMap<Visit, Long> implements VisitService
{
    private final VisitService visitService;

    public VisitServiceMapImpl(VisitService visitService)
    {
        this.visitService = visitService;
    }

    @Override
    public Set<Visit> findAll()
    {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object)
    {
        if(object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getId() == null ||
                object.getPet().getOwner().getId() == null)
        {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(object);
    }

    @Override
    public void delete(Visit object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
