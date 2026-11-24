package Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class PartieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nbJoueurs;
    private Integer nbIA;
    private LocalDateTime date;
    @ManyToOne
    //Gagnant
    private JoueurEntity gagnantEntity;

    @OneToMany
    @JoinColumn(name="partie_id", referencedColumnName = "id")
    private Set<MancheEntity> mancheEntities;

    @ManyToMany
    @JoinTable(
            name="parties_joueurs",
            joinColumns =@JoinColumn(name="partie_entity_id"),
            inverseJoinColumns=@JoinColumn(name = "joueur_entity_id")
    )
    private Set<JoueurEntity> joueurs;

    public PartieEntity(){}
    public PartieEntity(Integer nbJoueurs,Integer nbIA){
        this.nbJoueurs=nbJoueurs;
        this.nbIA=nbIA;
        this.date= LocalDateTime.now();
    }

    public Long getId(){
        return this.id;
    }

    public Integer getNbIA() {
        return nbIA;
    }

    public Integer getNbJoueurs() {
        return nbJoueurs;
    }

    public JoueurEntity getGagnant() {
        return gagnantEntity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Set<JoueurEntity> getJoueurs() {
        return joueurs;
    }

    public Set<MancheEntity> getMancheEntities() {
        return mancheEntities;
    }

    public void ajoutJoueur(JoueurEntity joueur){
        this.joueurs.add(joueur);
    }

}
