package Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class PartieEntity {
    @Id
    private Integer id;
    private Integer nbJoueurs;
    private Integer nbIA;
    private Date date;

    @ManyToOne
    private JoueurEntity joueurEntity;

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
}
