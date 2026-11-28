package Entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class CoupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne
    private MembreEntity membreEntity;

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MembreEntity getMembreEntity() {
        return membreEntity;
    }

    public void setMembreEntity(MembreEntity membreEntity) {
        this.membreEntity = membreEntity;
    }
}
