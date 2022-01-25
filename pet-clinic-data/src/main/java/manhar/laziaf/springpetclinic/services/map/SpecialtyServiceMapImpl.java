package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Specialty;
import manhar.laziaf.springpetclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialtyServiceMapImpl extends AbstractServiceMap<Specialty, Long> implements SpecialtyService
{
    @Override
    public Set<Specialty> findAll()
    {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object)
    {
        return super.save(object);
    }

    @Override
    public void delete(Specialty object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
