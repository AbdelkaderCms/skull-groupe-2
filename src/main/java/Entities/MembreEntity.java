package Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MembreEntity extends JoueurEntity{
    private String email;
    private String mdp;
    private Integer points;
    private Integer monnaie;
    private Boolean estBanni;

    @ManyToOne
    private SetEntity setEntity;

    @ManyToMany
    @JoinTable(
            name="membre_sets",
            joinColumns =@JoinColumn(name="membre_entity_id"),
            inverseJoinColumns=@JoinColumn(name = "set_entity_id")
    )
    private Set<SetEntity> sets;
    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getMonnaie() {
        return monnaie;
    }

    public Boolean getEstBanni() {
        return estBanni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setMonnaie(Integer monnaie) {
        this.monnaie = monnaie;
    }

    public void setEstBanni(Boolean estBanni) {
        this.estBanni = estBanni;
    }
}
