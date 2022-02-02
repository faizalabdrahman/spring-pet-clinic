package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.services.OwnerService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest
{
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Owner owner;
    Set<Owner> ownerSet;

    MockMvc mockMvc;


    @BeforeEach
    public void setUp()
    {
        owner = new Owner();
        owner.setId(1L);

        ownerSet = new HashSet<>();
        ownerSet.add(owner);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    public void listOwners() throws Exception
    {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    public void listOwnersByIndex() throws Exception
    {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    public void findOwners() throws Exception
    {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
    }

    @Test
    public void showOwner() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"));


    }
}