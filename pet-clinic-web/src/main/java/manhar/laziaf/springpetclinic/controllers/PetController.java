package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.model.PetType;
import manhar.laziaf.springpetclinic.services.OwnerService;
import manhar.laziaf.springpetclinic.services.PetService;
import manhar.laziaf.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class PetController
{
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService)
    {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("petTypeSet")
    public Collection<PetType> populatePetTypeSet()
    {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId)
    {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder binder)
    {
        binder.setDisallowedFields("id");
    }

    @GetMapping("/owners/{ownerId}/pets/new")
    public String initCreatePetForm(Owner owner, Model model)
    {
        Pet pet = new Pet();
        owner.getPetSet().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/new")
    public String processCreatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model)
    {
        if(pet.getName() != null && pet.isNew() && owner.getPet(pet.getName(), true) != null)
        {
            result.rejectValue("name", "duplicated", "already exists");
        }

        owner.getPetSet().add(pet);

        if(result.hasErrors())
        {
            model.addAttribute("pet", pet);

            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }
}
