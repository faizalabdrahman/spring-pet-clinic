package manhar.laziaf.springpetclinic.services.map;

import manhar.laziaf.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerServiceMapImplTest
{
    OwnerServiceMapImpl ownerServiceMapImpl;

    public final Long id = 1L;
    public final String lastName = "Smith";

    @BeforeEach
    public void setUp()
    {
        Owner owner1 = new Owner();
        owner1.setId(id);
        owner1.setLastName(lastName);

        ownerServiceMapImpl = new OwnerServiceMapImpl(new PetTypeServiceMapImpl(), new PetServiceMapImpl());
        ownerServiceMapImpl.save(owner1);
    }

    @Test
    public void findAll()
    {
        Set<Owner> ownerSet = ownerServiceMapImpl.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    public void findById()
    {
        Owner owner1 = ownerServiceMapImpl.findById(id);

        assertEquals(id, owner1.getId());
    }

    @Test
    public void save()
    {
        Owner owner2 = new Owner();
        owner2.setId(2L);

        ownerServiceMapImpl.save(owner2);

        assertEquals(2, ownerServiceMapImpl.findAll().size());
    }

    @Test
    public void delete()
    {
        ownerServiceMapImpl.delete(ownerServiceMapImpl.findById(id));

        assertEquals(0, ownerServiceMapImpl.findAll().size());
    }

    @Test
    public void deleteById()
    {
        ownerServiceMapImpl.deleteById(id);

        assertEquals(0, ownerServiceMapImpl.findAll().size());
    }

    @Test
    public void findByLastName()
    {
        Owner smith = ownerServiceMapImpl.findByLastName(lastName);

        assertEquals(id, smith.getId());
    }
}