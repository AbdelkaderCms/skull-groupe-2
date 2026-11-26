package Entities;

import jakarta.persistence.*;

@Entity
public class EvenementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private MembreEntity membreEntity;

    @ManyToOne
    private CoupEntity coupsEntity;

    public EvenementEntity(){}
    public EvenementEntity(MembreEntity membre, CoupEntity coup){
        this.membreEntity=membre;
        this.coupsEntity=coup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MembreEntity getMembreEntity() {
        return membreEntity;
    }

    public CoupEntity getCoupsEntity() {
        return coupsEntity;
    }
}
