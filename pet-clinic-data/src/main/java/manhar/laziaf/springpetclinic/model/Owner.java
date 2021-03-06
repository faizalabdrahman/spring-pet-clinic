package manhar.laziaf.springpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner extends Person
{
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> petSet = new HashSet<>();

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name)
    {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew)
    {
        name = name.toLowerCase();

        for (Pet pet : petSet)
        {
            if (!ignoreNew || !pet.isNew())
            {
                String compName = pet.getName();
                compName = compName == null ? "" : compName.toLowerCase();

                if (compName.equals(name))
                {
                    return pet;
                }
            }
        }

        return null;
    }
}
