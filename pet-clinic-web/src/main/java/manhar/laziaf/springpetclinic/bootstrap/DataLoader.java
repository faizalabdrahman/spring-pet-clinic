package manhar.laziaf.springpetclinic.bootstrap;

import manhar.laziaf.springpetclinic.model.*;
import manhar.laziaf.springpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        int count = petTypeService.findAll().size();

        if(count == 0)
        {
            loadData();
        }
    }

    private void loadData()
    {
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");
        PetType savedCatPetType = petTypeService.save(catPetType);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Woodlands Drive");
        owner1.setCity("Singapore");
        owner1.setTelephone("+65 1234 1234");

        Pet michealPet1 = new Pet();
        michealPet1.setPetType(savedDogPetType);
        michealPet1.setOwner(owner1);
        michealPet1.setBirthDate(LocalDate.now());
        michealPet1.setName("Growler");
        owner1.getPetSet().add(michealPet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("234 Fernvale Street");
        owner2.setCity("Singapore");
        owner2.setTelephone("65 2345 2345");

        Pet fionaPet1 = new Pet();
        fionaPet1.setPetType(savedCatPetType);
        fionaPet1.setOwner(owner2);
        fionaPet1.setBirthDate(LocalDate.now());
        fionaPet1.setName("Sonia");
        owner2.getPetSet().add(fionaPet1);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionaPet1);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialtySet().add(savedRadiology);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialtySet().add(savedSurgery);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
