package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EvenementEntity {
    @Id
    private Integer id;

    @ManyToOne
    private MembreEntity membreEntity;

    @ManyToOne
    private CoupEntity coupsEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
