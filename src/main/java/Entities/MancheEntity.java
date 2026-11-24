package Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MancheEntity {
    @Id
    private Integer id;
    private Boolean estEnCours;
    private Boolean estGagnee;

    @ManyToOne
    private JoueurEntity joueurEntity;

    @ManyToOne
    private TuileEntity tuileEntity;

    @OneToMany
    @JoinColumn(name="manche_id", referencedColumnName = "id")
    private Set<CoupEntity> coupEntities;

    public Integer getId() {
        return id;
    }

    public Boolean getEstEnCours() {
        return estEnCours;
    }

    public Boolean getEstGagnee() {
        return estGagnee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEstEnCours(Boolean estEnCours) {
        this.estEnCours = estEnCours;
    }

    public void setEstGagnee(Boolean estGagnee) {
        this.estGagnee = estGagnee;
    }
}
