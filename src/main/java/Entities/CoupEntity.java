package Entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class CoupEntity {
    @Id
    protected Integer id;

    @ManyToOne
    private MembreEntity membreEntity;

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
