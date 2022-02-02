package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.model.PetType;
import manhar.laziaf.springpetclinic.services.OwnerService;
import manhar.laziaf.springpetclinic.services.PetService;
import manhar.laziaf.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest
{
    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;

    Pet pet1;

    PetType petTypeDog;
    PetType petTypeCat;
    Set<PetType> petTypeSet;

    @BeforeEach
    public void setUp()
    {
        owner = new Owner();
        owner.setId(1L);

        pet1 = new Pet();
        pet1.setId(1L);

        petTypeDog = new PetType();
        petTypeDog.setId(1L);
        petTypeDog.setName("Dog");

        petTypeCat = new PetType();
        petTypeCat.setId(2L);
        petTypeCat.setName("Cat");

        petTypeSet = new HashSet<>();
        petTypeSet.add(petTypeDog);
        petTypeSet.add(petTypeCat);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    public void initCreatePetForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypeSet);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    public void processCreatePetForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypeSet);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    public void initUpdatePetForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.findById(anyLong())).thenReturn(pet1);
        when(petTypeService.findAll()).thenReturn(petTypeSet);

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    public void processUpdatePetForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypeSet);

        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }
}