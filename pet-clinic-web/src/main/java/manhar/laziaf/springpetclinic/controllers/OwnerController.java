package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OwnerController
{
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/find")
    public String initFindOwnersForm(Model model)
    {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindOwnersForm(Owner owner, BindingResult result, Model model)
    {
        if (owner.getLastName() == null)
        {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty())
        {
            result.rejectValue("lastName", "notFound", "not found");

            return "owners/findOwners";
        }
        else if (results.size() == 1)
        {
            owner = results.get(0);

            return "redirect:/owners/" + owner.getId();
        }
        else
        {
            model.addAttribute("owners", results);

            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId)
    {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping("/owners/new")
    public String initCreateOwnerForm(Model model)
    {
        model.addAttribute("owner", new Owner());

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreateOwnerForm(@Valid Owner owner, BindingResult result)
    {
        if(result.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model)
    {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId)
    {
        if(result.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else
        {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
