package manhar.laziaf.springpetclinic.services.springdatajpa;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.repositories.OwnerRepository;
import manhar.laziaf.springpetclinic.repositories.PetRepository;
import manhar.laziaf.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerJPAServiceImplTest
{
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    OwnerJPAServiceImpl ownerJPAServiceImpl;

    Owner owner;

    public final Long ID = 1L;
    public final String LAST_NAME = "smith";

    @BeforeEach
    public void setUp()
    {
        owner = new Owner();
        owner.setId(ID);
        owner.setLastName(LAST_NAME);
    }

    @Test
    public void findAll()
    {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(owner);

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> ownerSet = ownerJPAServiceImpl.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    public void findById()
    {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        Owner ownerFound = ownerJPAServiceImpl.findById(ID);

        assertNotNull(ownerFound);
    }

    @Test
    public void save()
    {
        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = ownerJPAServiceImpl.save(owner);

        assertNotNull(savedOwner);
    }

    @Test
    public void delete()
    {
        ownerJPAServiceImpl.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    public void deleteById()
    {
        ownerJPAServiceImpl.deleteById(ID);

        verify(ownerRepository, times(1)).deleteById(anyLong());

    }

    @Test
    public void findByLastName()
    {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner smith = ownerRepository.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());
    }
}