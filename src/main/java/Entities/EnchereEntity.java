package Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("Enchere")
public class EnchereEntity extends CoupEntity{
    private Integer valeur;

    public Integer getId() {
        return this.id;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
}

