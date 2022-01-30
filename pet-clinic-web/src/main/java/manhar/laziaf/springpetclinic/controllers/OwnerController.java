package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.model.Owner;
import manhar.laziaf.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController
{
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @GetMapping({"/owners", "/owners/index", "/owners/index/html"})
    public String listOwners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @GetMapping("/owners/find")
    public String initFindOwnersForm(Model model)
    {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @PostMapping("/owners/show")
    public String processFindOwnersForm(@ModelAttribute("owner") Owner owner)
    {
        Owner returnedOwner = ownerService.findByLastName(owner.getLastName());
        System.out.println(owner.getLastName());

        return "redirect:/owners/" + returnedOwner.getId();
    }

    @GetMapping("/owners/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model)
    {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owners/ownerDetails";
    }

    @GetMapping("/owners/new")
    public String initNewOwnerForm(Model model)
    {
        model.addAttribute("owner", new Owner());

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/create")
    public String processNewOwnerForm(@ModelAttribute("owner") Owner owner)
    {
        Owner savedOwner = ownerService.save(owner);

        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model)
    {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, @ModelAttribute("owner") Owner owner)
    {
        Owner savedOwner = ownerService.save(owner);

        return "redirect:/owners/" + savedOwner.getId();
    }
}
