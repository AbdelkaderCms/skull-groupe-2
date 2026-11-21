package skullgroupe2.skullgroupe2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity 
public class Tuile {

    @Id 
    @GeneratedValue 
    private Long id;

    private boolean estCachee ; // colonne booléenne : la tuile est-elle face cachée ?

    
    
}
