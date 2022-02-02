package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.model.Pet;
import manhar.laziaf.springpetclinic.model.PetType;
import manhar.laziaf.springpetclinic.services.OwnerService;
import manhar.laziaf.springpetclinic.services.PetService;
import manhar.laziaf.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController
{
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService)
    {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes()
    {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId)
    {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreatePetForm(Owner owner, Model model)
    {
        Pet pet = new Pet();
        owner.getPetSet().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model)
    {
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
        {
            result.rejectValue("name", "duplicate", "already exists");
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

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model)
    {
        model.addAttribute("pet", petService.findById(petId));

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet, BindingResult result, Owner owner, Model model)
    {
        if(result.hasErrors())
        {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            owner.getPetSet().add(pet);
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }
}
