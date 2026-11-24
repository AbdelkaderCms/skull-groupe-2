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

    
    //getters

    public Long getId() {
        return id; 
    }

    public boolean isEstCachee() { 
        return estCachee;
    }


    //setters

    public void setEstCachee(boolean estCachee) {
        this.estCachee = estCachee;
    }

}
