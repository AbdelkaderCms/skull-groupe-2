package Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class SetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer prix;

    @OneToMany
    @JoinColumn(name="set_id", referencedColumnName = "id")
    private Set<TuileEntity> tuileEntities;
    public SetEntity(){}
    public SetEntity(Integer prix){
        this.prix=prix;
    }
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

}
