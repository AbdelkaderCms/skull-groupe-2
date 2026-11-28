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
    private JoueurEntity joueurCourant;

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

    public JoueurEntity getJoueurEntity() {
        return joueurEntity;
    }

    public void setJoueurEntity(JoueurEntity joueurEntity) {
        this.joueurEntity = joueurEntity;
    }

    public JoueurEntity getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(JoueurEntity joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public Set<CoupEntity> getCoupEntities() {
        return coupEntities;
    }
}
