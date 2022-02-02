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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

    Owner owner1;
    Owner owner2;
    Set<Owner> ownerSet;

    MockMvc mockMvc;


    @BeforeEach
    public void setUp()
    {
        owner1 = new Owner();
        owner1.setId(1L);

        owner2 = new Owner();
        owner2.setId(2L);

        ownerSet = new HashSet<>();
        ownerSet.add(owner1);
        ownerSet.add(owner2);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    public void initFindOwnersForm() throws Exception
    {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    public void processFindOwnerFormReturnMany() throws Exception
    {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    public void processFindOwnerFormReturnOne() throws Exception
    {
        Owner owner3 = new Owner();
        owner3.setId(3L);

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner3));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/3"));
    }

    @Test
    public void showOwner() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"));
    }
}