package skullgroupe2.skullgroupe2.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity 
public class Joueur {

    @Id @GeneratedValue
    private Long id;
    
}
