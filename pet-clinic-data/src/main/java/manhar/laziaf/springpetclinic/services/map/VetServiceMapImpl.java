package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Vet;
import manhar.laziaf.springpetclinic.services.CrudService;

import java.util.Set;

public class VetServiceMapImpl extends AbstractServiceMap<Vet, Long> implements CrudService<Vet, Long>
{
    @Override
    public Set<Vet> findAll()
    {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object)
    {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Vet object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
