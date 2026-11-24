package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class SetEntity {
    @Id
    private Integer id;
    private Integer prix;

    @OneToMany
    @JoinColumn(name="set_id", referencedColumnName = "id")
    private Set<TuileEntity> tuileEntities;
}
