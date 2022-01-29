package manhar.laziaf.springpetclinic.controllers;

import manhar.laziaf.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String findOwners()
    {
        return "notimplemented";
    }

    @GetMapping("/owners/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model)
    {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owners/ownerDetails";
    }

}
