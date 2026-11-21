package skullgroupe2.skullgroupe2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Passe, Enchere et PoseTuile seront stockés dans cette même table
public abstract class Coup {

    @Id @GeneratedValue 
    private Long id ;
    

  
    // plusieurs coups joués par UN membre
    @ManyToOne
    private Membre membre;

    
}

